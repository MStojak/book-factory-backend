package hr.fer.is.app.controller;

import hr.fer.is.app.repository.UserRoleRepository;
import hr.fer.is.app.service.UserRoleService;
import hr.fer.is.app.service.dto.UserRoleDTO;
import hr.fer.is.app.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing {@link hr.fer.is.app.domain.UserRole}.
 */
@RestController
@RequestMapping("/api")
public class UserRoleResource {

    private final Logger log = LoggerFactory.getLogger(UserRoleResource.class);

    private static final String ENTITY_NAME = "userRole";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserRoleService userRoleService;

    private final UserRoleRepository userRoleRepository;

    public UserRoleResource(UserRoleService userRoleService, UserRoleRepository userRoleRepository) {
        this.userRoleService = userRoleService;
        this.userRoleRepository = userRoleRepository;
    }

    /**
     * {@code POST  /user-roles} : Create a new userRole.
     *
     * @param userRoleDTO the userRoleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userRoleDTO, or with status {@code 400 (Bad Request)} if the userRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-roles")
    public Mono<ResponseEntity<UserRoleDTO>> createUserRole(@Valid @RequestBody UserRoleDTO userRoleDTO) throws URISyntaxException {
        log.debug("REST request to save UserRole : {}", userRoleDTO);
        if (userRoleDTO.getId() != null) {
            throw new BadRequestAlertException("A new userRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return userRoleService
            .save(userRoleDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/user-roles/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /user-roles/:id} : Updates an existing userRole.
     *
     * @param id the id of the userRoleDTO to save.
     * @param userRoleDTO the userRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userRoleDTO,
     * or with status {@code 400 (Bad Request)} if the userRoleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-roles/{id}")
    public Mono<ResponseEntity<UserRoleDTO>> updateUserRole(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody UserRoleDTO userRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update UserRole : {}, {}", id, userRoleDTO);
        if (userRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return userRoleRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return userRoleService
                    .update(userRoleDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /user-roles/:id} : Partial updates given fields of an existing userRole, field will ignore if it is null
     *
     * @param id the id of the userRoleDTO to save.
     * @param userRoleDTO the userRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userRoleDTO,
     * or with status {@code 400 (Bad Request)} if the userRoleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userRoleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-roles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<UserRoleDTO>> partialUpdateUserRole(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody UserRoleDTO userRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserRole partially : {}, {}", id, userRoleDTO);
        if (userRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return userRoleRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<UserRoleDTO> result = userRoleService.partialUpdate(userRoleDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /user-roles} : get all the userRoles.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userRoles in body.
     */
    @GetMapping("/user-roles")
    public Mono<ResponseEntity<List<UserRoleDTO>>> getAllUserRoles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of UserRoles");
        return userRoleService
            .countAll()
            .zipWith(userRoleService.findAll(pageable).collectList())
            .map(countWithEntities ->
                ResponseEntity
                    .ok()
                    .headers(
                        PaginationUtil.generatePaginationHttpHeaders(
                            UriComponentsBuilder.fromHttpRequest(request),
                            new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                        )
                    )
                    .body(countWithEntities.getT2())
            );
    }

    /**
     * {@code GET  /user-roles/:id} : get the "id" userRole.
     *
     * @param id the id of the userRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-roles/{id}")
    public Mono<ResponseEntity<UserRoleDTO>> getUserRole(@PathVariable Long id) {
        log.debug("REST request to get UserRole : {}", id);
        Mono<UserRoleDTO> userRoleDTO = userRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userRoleDTO);
    }

    /**
     * {@code DELETE  /user-roles/:id} : delete the "id" userRole.
     *
     * @param id the id of the userRoleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-roles/{id}")
    public Mono<ResponseEntity<Void>> deleteUserRole(@PathVariable Long id) {
        log.debug("REST request to delete UserRole : {}", id);
        return userRoleService
            .delete(id)
            .then(
                Mono.just(
                    ResponseEntity
                        .noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }
}
