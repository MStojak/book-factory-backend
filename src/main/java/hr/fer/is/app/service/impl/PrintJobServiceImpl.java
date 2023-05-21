package hr.fer.is.app.service.impl;

import hr.fer.is.app.converter.PrintJobMapper;
import hr.fer.is.app.domain.PrintJob;
import hr.fer.is.app.domain.dto.PrintJobDTO;
import hr.fer.is.app.repository.PrintJobRepository;
import hr.fer.is.app.service.CoverTypeService;
import hr.fer.is.app.service.PrintJobService;
import hr.fer.is.app.service.PublisherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.List;

import static hr.fer.is.app.util.DateUtil.check;

@Service
public class PrintJobServiceImpl implements PrintJobService {

    private final PrintJobRepository printJobRepository;
    private final PrintJobMapper printJobMapper;
    private final PublisherService publisherService;
    private final CoverTypeService coverTypeService;

    public PrintJobServiceImpl(PrintJobRepository printJobRepository, PrintJobMapper printJobMapper, PublisherService publisherService, CoverTypeService coverTypeService) {
        this.printJobRepository = printJobRepository;
        this.printJobMapper = printJobMapper;
        this.publisherService = publisherService;
        this.coverTypeService = coverTypeService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrintJobDTO> findAll() {
        return printJobRepository.findAll().stream()
                .map(printJobMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PrintJobDTO findById(Long id) {
        return printJobRepository.findById(id)
                .map(printJobMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("PrintJob not found"));
    }

    @Override
    @Transactional
    public PrintJobDTO save(PrintJobDTO printJobDTO) {
        check(printJobDTO);
        PrintJob printJob = printJobMapper.toEntity(printJobDTO);

        printJob.setPublisher(publisherService.getRawById(printJobDTO.getPublisherId()));
        printJob.setCoverType(coverTypeService.getRawById(printJobDTO.getCoverTypeId()));

        PrintJob savedPrintJob = printJobRepository.save(printJob);
        return printJobMapper.toDTO(savedPrintJob);
    }

    @Override
    @Transactional
    public PrintJobDTO update(Long id, PrintJobDTO printJobDTO) {
        check(printJobDTO);
        PrintJob printJob = printJobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PrintJob not found"));

        printJob.setPublisher(publisherService.getRawById(printJobDTO.getPublisherId()));
        printJob.setCoverType(coverTypeService.getRawById(printJobDTO.getCoverTypeId()));

        printJobMapper.updateEntityFromDto(printJobDTO, printJob);
        printJobRepository.save(printJob);
        return printJobMapper.toDTO(printJob);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        PrintJob printJob = printJobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PrintJob not found"));

        printJobRepository.delete(printJob);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrintJobDTO> findAllByPublisherId(Long id) {
        return printJobRepository.findAllByPublisherId(id).stream()
                .map(printJobMapper::toDTO)
                .toList();
    }

}
