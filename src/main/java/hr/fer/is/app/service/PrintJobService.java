package hr.fer.is.app.service;

import hr.fer.is.app.domain.dto.PrintJobDTO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface PrintJobService {

    List<PrintJobDTO> findAll();

    PrintJobDTO findById(Long id);

    PrintJobDTO save(@Valid PrintJobDTO printJobDTO);

    PrintJobDTO update(Long id, @Valid PrintJobDTO printJobDTO);

    void delete(Long id);

    public List<PrintJobDTO> findAllByPublisherId(Long id);

}
