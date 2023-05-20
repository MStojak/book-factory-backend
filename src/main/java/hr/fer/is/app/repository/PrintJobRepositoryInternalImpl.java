package hr.fer.is.app.repository;

import hr.fer.is.app.domain.PrintJob;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoinCondition;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Spring Data R2DBC custom repository implementation for the PrintJob entity.
 */
@SuppressWarnings("unused")
class PrintJobRepositoryInternalImpl extends SimpleR2dbcRepository<PrintJob, Long> implements PrintJobRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final PublisherRowMapper publisherMapper;
    private final CoverTypeRowMapper covertypeMapper;
    private final PrintJobRowMapper printjobMapper;

    private static final Table entityTable = Table.aliased("print_job", EntityManager.ENTITY_ALIAS);
    private static final Table publisherTable = Table.aliased("publisher", "publisher");
    private static final Table coverTypeTable = Table.aliased("cover_type", "coverType");

    public PrintJobRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        PublisherRowMapper publisherMapper,
        CoverTypeRowMapper covertypeMapper,
        PrintJobRowMapper printjobMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(PrintJob.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.publisherMapper = publisherMapper;
        this.covertypeMapper = covertypeMapper;
        this.printjobMapper = printjobMapper;
    }

    @Override
    public Flux<PrintJob> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<PrintJob> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = PrintJobSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(PublisherSqlHelper.getColumns(publisherTable, "publisher"));
        columns.addAll(CoverTypeSqlHelper.getColumns(coverTypeTable, "coverType"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(publisherTable)
            .on(Column.create("publisher_id", entityTable))
            .equals(Column.create("id", publisherTable))
            .leftOuterJoin(coverTypeTable)
            .on(Column.create("cover_type_id", entityTable))
            .equals(Column.create("id", coverTypeTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, PrintJob.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<PrintJob> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<PrintJob> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private PrintJob process(Row row, RowMetadata metadata) {
        PrintJob entity = printjobMapper.apply(row, "e");
        entity.setPublisher(publisherMapper.apply(row, "publisher"));
        entity.setCoverType(covertypeMapper.apply(row, "coverType"));
        return entity;
    }

    @Override
    public <S extends PrintJob> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
