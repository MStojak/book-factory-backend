package hr.fer.is.app.service.impl;

import hr.fer.is.app.converter.PrintJobMapper;
import hr.fer.is.app.domain.PrintJob;
import hr.fer.is.app.domain.dto.PrintJobDTO;
import hr.fer.is.app.repository.PrintJobRepository;
import hr.fer.is.app.service.PrintJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PrintJob}.
 */
@Service
@Transactional
public class PrintJobServiceImpl implements PrintJobService {

    private final Logger log = LoggerFactory.getLogger(PrintJobServiceImpl.class);

    private final PrintJobRepository printJobRepository;

    private final PrintJobMapper printJobMapper;

    public PrintJobServiceImpl(PrintJobRepository printJobRepository, PrintJobMapper printJobMapper) {
        this.printJobRepository = printJobRepository;
        this.printJobMapper = printJobMapper;
    }

    @Override
    public Mono<PrintJobDTO> save(PrintJobDTO printJobDTO) {
        log.debug("Request to save PrintJob : {}", printJobDTO);
        return printJobRepository.save(printJobMapper.toEntity(printJobDTO)).map(printJobMapper::toDto);
    }

    @Override
    public Mono<PrintJobDTO> update(PrintJobDTO printJobDTO) {
        log.debug("Request to update PrintJob : {}", printJobDTO);
        return printJobRepository.save(printJobMapper.toEntity(printJobDTO)).map(printJobMapper::toDto);
    }

    @Override
    public Mono<PrintJobDTO> partialUpdate(PrintJobDTO printJobDTO) {
        log.debug("Request to partially update PrintJob : {}", printJobDTO);

        return printJobRepository
            .findById(printJobDTO.getId())
            .map(existingPrintJob -> {
                printJobMapper.partialUpdate(existingPrintJob, printJobDTO);

                return existingPrintJob;
            })
            .flatMap(printJobRepository::save)
            .map(printJobMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PrintJobDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrintJobs");
        return printJobRepository.findAllBy(pageable).map(printJobMapper::toDto);
    }

    public Mono<Long> countAll() {
        return printJobRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PrintJobDTO> findOne(Long id) {
        log.debug("Request to get PrintJob : {}", id);
        return printJobRepository.findById(id).map(printJobMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PrintJob : {}", id);
        return printJobRepository.deleteById(id);
    }
}
