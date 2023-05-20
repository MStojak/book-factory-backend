package hr.fer.is.app.service;

import hr.fer.is.app.domain.CoverType;
import hr.fer.is.app.domain.dto.CoverTypeDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link CoverType}.
 */
public interface CoverTypeService {
    /**
     * Save a coverType.
     *
     * @param coverTypeDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<CoverTypeDTO> save(CoverTypeDTO coverTypeDTO);

    /**
     * Updates a coverType.
     *
     * @param coverTypeDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<CoverTypeDTO> update(CoverTypeDTO coverTypeDTO);

    /**
     * Partially updates a coverType.
     *
     * @param coverTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<CoverTypeDTO> partialUpdate(CoverTypeDTO coverTypeDTO);

    /**
     * Get all the coverTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<CoverTypeDTO> findAll(Pageable pageable);

    /**
     * Returns the number of coverTypes available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" coverType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<CoverTypeDTO> findOne(Long id);

    /**
     * Delete the "id" coverType.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
