package hr.fer.is.app.repository;

import hr.fer.is.app.domain.Publisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Publisher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PublisherRepository extends ReactiveCrudRepository<Publisher, Long>, PublisherRepositoryInternal {
    Flux<Publisher> findAllBy(Pageable pageable);

    @Override
    <S extends Publisher> Mono<S> save(S entity);

    @Override
    Flux<Publisher> findAll();

    @Override
    Mono<Publisher> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PublisherRepositoryInternal {
    <S extends Publisher> Mono<S> save(S entity);

    Flux<Publisher> findAllBy(Pageable pageable);

    Flux<Publisher> findAll();

    Mono<Publisher> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Publisher> findAllBy(Pageable pageable, Criteria criteria);

}
