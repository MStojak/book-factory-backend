package hr.fer.is.app.controller;

import hr.fer.is.app.domain.dto.CoverTypeDTO;
import hr.fer.is.app.exception.BadRequestAlertException;
import hr.fer.is.app.repository.CoverTypeRepository;
import hr.fer.is.app.service.CoverTypeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing {@link hr.fer.is.app.domain.CoverType}.
 */
@RestController
@RequestMapping("/api")
public class CoverTypeResource {

    private final Logger log = LoggerFactory.getLogger(CoverTypeResource.class);

    private static final String ENTITY_NAME = "coverType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CoverTypeService coverTypeService;

    private final CoverTypeRepository coverTypeRepository;

    public CoverTypeResource(CoverTypeService coverTypeService, CoverTypeRepository coverTypeRepository) {
        this.coverTypeService = coverTypeService;
        this.coverTypeRepository = coverTypeRepository;
    }

    /**
     * {@code POST  /cover-types} : Create a new coverType.
     *
     * @param coverTypeDTO the coverTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new coverTypeDTO, or with status {@code 400 (Bad Request)} if the coverType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cover-types")
    public Mono<ResponseEntity<CoverTypeDTO>> createCoverType(@Valid @RequestBody CoverTypeDTO coverTypeDTO) throws URISyntaxException {
        log.debug("REST request to save CoverType : {}", coverTypeDTO);
        if (coverTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new coverType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return coverTypeService
            .save(coverTypeDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/cover-types/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /cover-types/:id} : Updates an existing coverType.
     *
     * @param id the id of the coverTypeDTO to save.
     * @param coverTypeDTO the coverTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coverTypeDTO,
     * or with status {@code 400 (Bad Request)} if the coverTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the coverTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cover-types/{id}")
    public Mono<ResponseEntity<CoverTypeDTO>> updateCoverType(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CoverTypeDTO coverTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CoverType : {}, {}", id, coverTypeDTO);
        if (coverTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, coverTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return coverTypeRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return coverTypeService
                    .update(coverTypeDTO)
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
     * {@code PATCH  /cover-types/:id} : Partial updates given fields of an existing coverType, field will ignore if it is null
     *
     * @param id the id of the coverTypeDTO to save.
     * @param coverTypeDTO the coverTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coverTypeDTO,
     * or with status {@code 400 (Bad Request)} if the coverTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the coverTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the coverTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cover-types/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CoverTypeDTO>> partialUpdateCoverType(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CoverTypeDTO coverTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CoverType partially : {}, {}", id, coverTypeDTO);
        if (coverTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, coverTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return coverTypeRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CoverTypeDTO> result = coverTypeService.partialUpdate(coverTypeDTO);

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
     * {@code GET  /cover-types} : get all the coverTypes.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of coverTypes in body.
     */
    @GetMapping("/cover-types")
    public Mono<ResponseEntity<List<CoverTypeDTO>>> getAllCoverTypes(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of CoverTypes");
        return coverTypeService
            .countAll()
            .zipWith(coverTypeService.findAll(pageable).collectList())
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
     * {@code GET  /cover-types/:id} : get the "id" coverType.
     *
     * @param id the id of the coverTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the coverTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cover-types/{id}")
    public Mono<ResponseEntity<CoverTypeDTO>> getCoverType(@PathVariable Long id) {
        log.debug("REST request to get CoverType : {}", id);
        Mono<CoverTypeDTO> coverTypeDTO = coverTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coverTypeDTO);
    }

    /**
     * {@code DELETE  /cover-types/:id} : delete the "id" coverType.
     *
     * @param id the id of the coverTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cover-types/{id}")
    public Mono<ResponseEntity<Void>> deleteCoverType(@PathVariable Long id) {
        log.debug("REST request to delete CoverType : {}", id);
        return coverTypeService
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
