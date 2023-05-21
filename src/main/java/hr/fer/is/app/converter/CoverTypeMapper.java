package hr.fer.is.app.converter;

import hr.fer.is.app.domain.dto.CoverTypeDTO;
import hr.fer.is.app.domain.CoverType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CoverTypeMapper {

    CoverType toEntity(CoverTypeDTO coverTypeDTO);

    CoverTypeDTO toDto(CoverType coverType);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CoverTypeDTO dto, @MappingTarget CoverType entity);
}
