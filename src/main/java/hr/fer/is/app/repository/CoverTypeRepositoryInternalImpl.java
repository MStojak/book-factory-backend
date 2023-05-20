package hr.fer.is.app.repository;

import hr.fer.is.app.domain.CoverType;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoin;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Spring Data R2DBC custom repository implementation for the CoverType entity.
 */
@SuppressWarnings("unused")
class CoverTypeRepositoryInternalImpl extends SimpleR2dbcRepository<CoverType, Long> implements CoverTypeRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final CoverTypeRowMapper covertypeMapper;

    private static final Table entityTable = Table.aliased("cover_type", EntityManager.ENTITY_ALIAS);

    public CoverTypeRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        CoverTypeRowMapper covertypeMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(CoverType.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.covertypeMapper = covertypeMapper;
    }

    @Override
    public Flux<CoverType> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<CoverType> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = CoverTypeSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, CoverType.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<CoverType> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<CoverType> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private CoverType process(Row row, RowMetadata metadata) {
        CoverType entity = covertypeMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends CoverType> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
