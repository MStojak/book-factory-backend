package hr.fer.is.app.controller;

import hr.fer.is.app.repository.PublisherRepository;
import hr.fer.is.app.service.PublisherService;
import hr.fer.is.app.service.dto.PublisherDTO;
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
 * REST controller for managing {@link hr.fer.is.app.domain.Publisher}.
 */
@RestController
@RequestMapping("/api")
public class PublisherResource {

    private final Logger log = LoggerFactory.getLogger(PublisherResource.class);

    private static final String ENTITY_NAME = "publisher";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PublisherService publisherService;

    private final PublisherRepository publisherRepository;

    public PublisherResource(PublisherService publisherService, PublisherRepository publisherRepository) {
        this.publisherService = publisherService;
        this.publisherRepository = publisherRepository;
    }

    /**
     * {@code POST  /publishers} : Create a new publisher.
     *
     * @param publisherDTO the publisherDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new publisherDTO, or with status {@code 400 (Bad Request)} if the publisher has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/publishers")
    public Mono<ResponseEntity<PublisherDTO>> createPublisher(@Valid @RequestBody PublisherDTO publisherDTO) throws URISyntaxException {
        log.debug("REST request to save Publisher : {}", publisherDTO);
        if (publisherDTO.getId() != null) {
            throw new BadRequestAlertException("A new publisher cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return publisherService
            .save(publisherDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/publishers/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /publishers/:id} : Updates an existing publisher.
     *
     * @param id the id of the publisherDTO to save.
     * @param publisherDTO the publisherDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated publisherDTO,
     * or with status {@code 400 (Bad Request)} if the publisherDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the publisherDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/publishers/{id}")
    public Mono<ResponseEntity<PublisherDTO>> updatePublisher(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PublisherDTO publisherDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Publisher : {}, {}", id, publisherDTO);
        if (publisherDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, publisherDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return publisherRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return publisherService
                    .update(publisherDTO)
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
     * {@code PATCH  /publishers/:id} : Partial updates given fields of an existing publisher, field will ignore if it is null
     *
     * @param id the id of the publisherDTO to save.
     * @param publisherDTO the publisherDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated publisherDTO,
     * or with status {@code 400 (Bad Request)} if the publisherDTO is not valid,
     * or with status {@code 404 (Not Found)} if the publisherDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the publisherDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/publishers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PublisherDTO>> partialUpdatePublisher(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PublisherDTO publisherDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Publisher partially : {}, {}", id, publisherDTO);
        if (publisherDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, publisherDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return publisherRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PublisherDTO> result = publisherService.partialUpdate(publisherDTO);

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
     * {@code GET  /publishers} : get all the publishers.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of publishers in body.
     */
    @GetMapping("/publishers")
    public Mono<ResponseEntity<List<PublisherDTO>>> getAllPublishers(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of Publishers");
        return publisherService
            .countAll()
            .zipWith(publisherService.findAll(pageable).collectList())
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
     * {@code GET  /publishers/:id} : get the "id" publisher.
     *
     * @param id the id of the publisherDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the publisherDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/publishers/{id}")
    public Mono<ResponseEntity<PublisherDTO>> getPublisher(@PathVariable Long id) {
        log.debug("REST request to get Publisher : {}", id);
        Mono<PublisherDTO> publisherDTO = publisherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(publisherDTO);
    }

    /**
     * {@code DELETE  /publishers/:id} : delete the "id" publisher.
     *
     * @param id the id of the publisherDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/publishers/{id}")
    public Mono<ResponseEntity<Void>> deletePublisher(@PathVariable Long id) {
        log.debug("REST request to delete Publisher : {}", id);
        return publisherService
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
