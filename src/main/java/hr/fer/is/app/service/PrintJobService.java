package hr.fer.is.app.service;


import hr.fer.is.app.domain.dto.PrintJobDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link hr.fer.is.app.domain.PrintJob}.
 */
public interface PrintJobService {
    /**
     * Save a printJob.
     *
     * @param printJobDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PrintJobDTO> save(PrintJobDTO printJobDTO);

    /**
     * Updates a printJob.
     *
     * @param printJobDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PrintJobDTO> update(PrintJobDTO printJobDTO);

    /**
     * Partially updates a printJob.
     *
     * @param printJobDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PrintJobDTO> partialUpdate(PrintJobDTO printJobDTO);

    /**
     * Get all the printJobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PrintJobDTO> findAll(Pageable pageable);

    /**
     * Returns the number of printJobs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" printJob.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PrintJobDTO> findOne(Long id);

    /**
     * Delete the "id" printJob.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
