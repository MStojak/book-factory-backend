package hr.fer.is.app.repository;

import hr.fer.is.app.domain.CoverType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CoverType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoverTypeRepository extends ReactiveCrudRepository<CoverType, Long>, CoverTypeRepositoryInternal {
    Flux<CoverType> findAllBy(Pageable pageable);

    @Override
    <S extends CoverType> Mono<S> save(S entity);

    @Override
    Flux<CoverType> findAll();

    @Override
    Mono<CoverType> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CoverTypeRepositoryInternal {
    <S extends CoverType> Mono<S> save(S entity);

    Flux<CoverType> findAllBy(Pageable pageable);

    Flux<CoverType> findAll();

    Mono<CoverType> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CoverType> findAllBy(Pageable pageable, Criteria criteria);

}
