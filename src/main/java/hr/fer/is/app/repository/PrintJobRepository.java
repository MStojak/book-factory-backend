package hr.fer.is.app.repository;

import hr.fer.is.app.domain.PrintJob;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PrintJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrintJobRepository extends ReactiveCrudRepository<PrintJob, Long>, PrintJobRepositoryInternal {
    Flux<PrintJob> findAllBy(Pageable pageable);

    @Query("SELECT * FROM print_job entity WHERE entity.publisher_id = :id")
    Flux<PrintJob> findByPublisher(Long id);

    @Query("SELECT * FROM print_job entity WHERE entity.publisher_id IS NULL")
    Flux<PrintJob> findAllWherePublisherIsNull();

    @Query("SELECT * FROM print_job entity WHERE entity.cover_type_id = :id")
    Flux<PrintJob> findByCoverType(Long id);

    @Query("SELECT * FROM print_job entity WHERE entity.cover_type_id IS NULL")
    Flux<PrintJob> findAllWhereCoverTypeIsNull();

    @Override
    <S extends PrintJob> Mono<S> save(S entity);

    @Override
    Flux<PrintJob> findAll();

    @Override
    Mono<PrintJob> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PrintJobRepositoryInternal {
    <S extends PrintJob> Mono<S> save(S entity);

    Flux<PrintJob> findAllBy(Pageable pageable);

    Flux<PrintJob> findAll();

    Mono<PrintJob> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PrintJob> findAllBy(Pageable pageable, Criteria criteria);

}
