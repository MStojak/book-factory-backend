package hr.fer.is.app.service.impl;

import hr.fer.is.app.converter.CoverTypeMapper;
import hr.fer.is.app.domain.CoverType;
import hr.fer.is.app.domain.dto.CoverTypeDTO;
import hr.fer.is.app.repository.CoverTypeRepository;
import hr.fer.is.app.service.CoverTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link CoverType}.
 */
@Service
@Transactional
public class CoverTypeServiceImpl implements CoverTypeService {

    private final Logger log = LoggerFactory.getLogger(CoverTypeServiceImpl.class);

    private final CoverTypeRepository coverTypeRepository;

    private final CoverTypeMapper coverTypeMapper;

    public CoverTypeServiceImpl(CoverTypeRepository coverTypeRepository, CoverTypeMapper coverTypeMapper) {
        this.coverTypeRepository = coverTypeRepository;
        this.coverTypeMapper = coverTypeMapper;
    }

    @Override
    public Mono<CoverTypeDTO> save(CoverTypeDTO coverTypeDTO) {
        log.debug("Request to save CoverType : {}", coverTypeDTO);
        return coverTypeRepository.save(coverTypeMapper.toEntity(coverTypeDTO)).map(coverTypeMapper::toDto);
    }

    @Override
    public Mono<CoverTypeDTO> update(CoverTypeDTO coverTypeDTO) {
        log.debug("Request to update CoverType : {}", coverTypeDTO);
        return coverTypeRepository.save(coverTypeMapper.toEntity(coverTypeDTO)).map(coverTypeMapper::toDto);
    }

    @Override
    public Mono<CoverTypeDTO> partialUpdate(CoverTypeDTO coverTypeDTO) {
        log.debug("Request to partially update CoverType : {}", coverTypeDTO);

        return coverTypeRepository
            .findById(coverTypeDTO.getId())
            .map(existingCoverType -> {
                coverTypeMapper.partialUpdate(existingCoverType, coverTypeDTO);

                return existingCoverType;
            })
            .flatMap(coverTypeRepository::save)
            .map(coverTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<CoverTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CoverTypes");
        return coverTypeRepository.findAllBy(pageable).map(coverTypeMapper::toDto);
    }

    public Mono<Long> countAll() {
        return coverTypeRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<CoverTypeDTO> findOne(Long id) {
        log.debug("Request to get CoverType : {}", id);
        return coverTypeRepository.findById(id).map(coverTypeMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CoverType : {}", id);
        return coverTypeRepository.deleteById(id);
    }
}
