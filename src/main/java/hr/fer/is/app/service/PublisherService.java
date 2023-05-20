package hr.fer.is.app.service;

import hr.fer.is.app.domain.dto.PublisherDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link hr.fer.is.app.domain.Publisher}.
 */
public interface PublisherService {
    /**
     * Save a publisher.
     *
     * @param publisherDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PublisherDTO> save(PublisherDTO publisherDTO);

    /**
     * Updates a publisher.
     *
     * @param publisherDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PublisherDTO> update(PublisherDTO publisherDTO);

    /**
     * Partially updates a publisher.
     *
     * @param publisherDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PublisherDTO> partialUpdate(PublisherDTO publisherDTO);

    /**
     * Get all the publishers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PublisherDTO> findAll(Pageable pageable);

    /**
     * Returns the number of publishers available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" publisher.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PublisherDTO> findOne(Long id);

    /**
     * Delete the "id" publisher.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
