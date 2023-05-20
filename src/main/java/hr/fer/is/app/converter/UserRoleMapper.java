package hr.fer.is.app.converter;

import hr.fer.is.app.domain.UserRole;
import hr.fer.is.app.domain.dto.UserRoleDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link UserRole} and its DTO {@link UserRoleDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserRoleMapper extends EntityMapper<UserRoleDTO, UserRole> {}
