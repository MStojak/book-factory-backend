package hr.fer.is.app.converter;

import hr.fer.is.app.domain.UserRole;
import hr.fer.is.app.domain.dto.UserRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

    UserRole toEntity(UserRoleDTO userRoleDTO);

    UserRoleDTO toDto(UserRole userRole);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UserRoleDTO dto, @MappingTarget UserRole entity);
}
