package hr.fer.is.app.service;

import java.util.List;
import hr.fer.is.app.domain.dto.ProcessDTO;

public interface ProcessService {
    ProcessDTO getById(Integer id);
    List<ProcessDTO> getAll();
}
