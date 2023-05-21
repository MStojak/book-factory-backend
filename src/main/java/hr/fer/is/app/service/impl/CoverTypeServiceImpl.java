package hr.fer.is.app.service.impl;

import hr.fer.is.app.converter.CoverTypeMapper;
import hr.fer.is.app.domain.CoverType;
import hr.fer.is.app.domain.dto.CoverTypeDTO;
import hr.fer.is.app.repository.CoverTypeRepository;
import hr.fer.is.app.service.CoverTypeService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoverTypeServiceImpl implements CoverTypeService {

    private final CoverTypeRepository coverTypeRepository;
    private final CoverTypeMapper coverTypeMapper;

    public CoverTypeServiceImpl(CoverTypeRepository coverTypeRepository, CoverTypeMapper coverTypeMapper) {
        this.coverTypeRepository = coverTypeRepository;
        this.coverTypeMapper = coverTypeMapper;
    }

    @Override
    public CoverTypeDTO create(CoverTypeDTO coverTypeDTO) {
        CoverType coverType = coverTypeMapper.toEntity(coverTypeDTO);
        return coverTypeMapper.toDto(coverTypeRepository.save(coverType));
    }

    @Override
    public CoverTypeDTO update(Integer id, CoverTypeDTO coverTypeDTO) {
        CoverType coverType = coverTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CoverType not found"));
        coverTypeMapper.updateEntityFromDto(coverTypeDTO, coverType);
        return coverTypeMapper.toDto(coverTypeRepository.save(coverType));
    }

    @Override
    public void delete(Integer id) {
        CoverType coverType = coverTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CoverType not found"));
        coverTypeRepository.delete(coverType);
    }

    @Override
    public List<CoverTypeDTO> getAll() {
        return coverTypeRepository.findAll().stream().map(coverTypeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CoverTypeDTO getById(Integer id) {
        CoverType coverType = coverTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CoverType not found"));
        return coverTypeMapper.toDto(coverType);
    }

    @Override
    public CoverType getRawById(Integer id) {
        return coverTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CoverType not found"));
    }
}