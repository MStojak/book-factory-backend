package hr.fer.is.app.service.impl;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hr.fer.is.app.domain.dto.ProcessDTO;
import hr.fer.is.app.repository.ProcessRepository;
import hr.fer.is.app.service.ProcessService;

import javax.persistence.EntityNotFoundException;

@Service
public class ProcessServiceImpl implements ProcessService {

    private final ProcessRepository processRepository;

    public ProcessServiceImpl(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ProcessDTO getById(Integer id) {
        return processRepository.findById(id)
                .map(process -> new ProcessDTO(process.getId(), process.getName(), process.getDescription()))
                .orElseThrow(() -> new EntityNotFoundException("Process not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProcessDTO> getAll() {
        return processRepository.findAll().stream()
                .map(process -> new ProcessDTO(process.getId(), process.getName(), process.getDescription()))
                .collect(Collectors.toList());
    }
}
