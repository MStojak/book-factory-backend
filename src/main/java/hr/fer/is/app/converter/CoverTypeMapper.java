package hr.fer.is.app.converter;

import hr.fer.is.app.domain.dto.CoverTypeDTO;
import hr.fer.is.app.domain.CoverType;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link CoverType} and its DTO {@link CoverTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface CoverTypeMapper extends EntityMapper<CoverTypeDTO, CoverType> {}
