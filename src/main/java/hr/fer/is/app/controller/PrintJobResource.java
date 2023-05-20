package hr.fer.is.app.controller;

import hr.fer.is.app.repository.PrintJobRepository;
import hr.fer.is.app.service.PrintJobService;
import hr.fer.is.app.service.dto.PrintJobDTO;
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
 * REST controller for managing {@link hr.fer.is.app.domain.PrintJob}.
 */
@RestController
@RequestMapping("/api")
public class PrintJobResource {

    private final Logger log = LoggerFactory.getLogger(PrintJobResource.class);

    private static final String ENTITY_NAME = "printJob";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrintJobService printJobService;

    private final PrintJobRepository printJobRepository;

    public PrintJobResource(PrintJobService printJobService, PrintJobRepository printJobRepository) {
        this.printJobService = printJobService;
        this.printJobRepository = printJobRepository;
    }

    /**
     * {@code POST  /print-jobs} : Create a new printJob.
     *
     * @param printJobDTO the printJobDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new printJobDTO, or with status {@code 400 (Bad Request)} if the printJob has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/print-jobs")
    public Mono<ResponseEntity<PrintJobDTO>> createPrintJob(@Valid @RequestBody PrintJobDTO printJobDTO) throws URISyntaxException {
        log.debug("REST request to save PrintJob : {}", printJobDTO);
        if (printJobDTO.getId() != null) {
            throw new BadRequestAlertException("A new printJob cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return printJobService
            .save(printJobDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/print-jobs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /print-jobs/:id} : Updates an existing printJob.
     *
     * @param id the id of the printJobDTO to save.
     * @param printJobDTO the printJobDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated printJobDTO,
     * or with status {@code 400 (Bad Request)} if the printJobDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the printJobDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/print-jobs/{id}")
    public Mono<ResponseEntity<PrintJobDTO>> updatePrintJob(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PrintJobDTO printJobDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PrintJob : {}, {}", id, printJobDTO);
        if (printJobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, printJobDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return printJobRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return printJobService
                    .update(printJobDTO)
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
     * {@code PATCH  /print-jobs/:id} : Partial updates given fields of an existing printJob, field will ignore if it is null
     *
     * @param id the id of the printJobDTO to save.
     * @param printJobDTO the printJobDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated printJobDTO,
     * or with status {@code 400 (Bad Request)} if the printJobDTO is not valid,
     * or with status {@code 404 (Not Found)} if the printJobDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the printJobDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/print-jobs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PrintJobDTO>> partialUpdatePrintJob(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PrintJobDTO printJobDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PrintJob partially : {}, {}", id, printJobDTO);
        if (printJobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, printJobDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return printJobRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PrintJobDTO> result = printJobService.partialUpdate(printJobDTO);

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
     * {@code GET  /print-jobs} : get all the printJobs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of printJobs in body.
     */
    @GetMapping("/print-jobs")
    public Mono<ResponseEntity<List<PrintJobDTO>>> getAllPrintJobs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PrintJobs");
        return printJobService
            .countAll()
            .zipWith(printJobService.findAll(pageable).collectList())
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
     * {@code GET  /print-jobs/:id} : get the "id" printJob.
     *
     * @param id the id of the printJobDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the printJobDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/print-jobs/{id}")
    public Mono<ResponseEntity<PrintJobDTO>> getPrintJob(@PathVariable Long id) {
        log.debug("REST request to get PrintJob : {}", id);
        Mono<PrintJobDTO> printJobDTO = printJobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(printJobDTO);
    }

    /**
     * {@code DELETE  /print-jobs/:id} : delete the "id" printJob.
     *
     * @param id the id of the printJobDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/print-jobs/{id}")
    public Mono<ResponseEntity<Void>> deletePrintJob(@PathVariable Long id) {
        log.debug("REST request to delete PrintJob : {}", id);
        return printJobService
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
