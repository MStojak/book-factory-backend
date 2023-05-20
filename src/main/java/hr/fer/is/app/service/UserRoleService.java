package hr.fer.is.app.service;

import hr.fer.is.app.domain.dto.UserRoleDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link hr.fer.is.app.domain.UserRole}.
 */
public interface UserRoleService {
    /**
     * Save a userRole.
     *
     * @param userRoleDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<UserRoleDTO> save(UserRoleDTO userRoleDTO);

    /**
     * Updates a userRole.
     *
     * @param userRoleDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<UserRoleDTO> update(UserRoleDTO userRoleDTO);

    /**
     * Partially updates a userRole.
     *
     * @param userRoleDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<UserRoleDTO> partialUpdate(UserRoleDTO userRoleDTO);

    /**
     * Get all the userRoles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<UserRoleDTO> findAll(Pageable pageable);

    /**
     * Returns the number of userRoles available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" userRole.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<UserRoleDTO> findOne(Long id);

    /**
     * Delete the "id" userRole.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
