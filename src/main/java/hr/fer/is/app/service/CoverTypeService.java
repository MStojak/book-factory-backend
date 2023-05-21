package hr.fer.is.app.service;

import hr.fer.is.app.domain.CoverType;
import hr.fer.is.app.domain.dto.CoverTypeDTO;

import java.util.List;

public interface CoverTypeService {
    CoverTypeDTO create(CoverTypeDTO coverTypeDTO);
    CoverTypeDTO update(Integer id, CoverTypeDTO coverTypeDTO);
    void delete(Integer id);
    List<CoverTypeDTO> getAll();
    CoverTypeDTO getById(Integer id);
    CoverType getRawById(Integer id);
}