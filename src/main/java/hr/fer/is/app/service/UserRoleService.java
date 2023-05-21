package hr.fer.is.app.service;

import hr.fer.is.app.domain.UserRole;
import hr.fer.is.app.domain.dto.UserRoleDTO;

public interface UserRoleService {
    UserRoleDTO getById(Integer id);
    public UserRole getRawById(Integer id);
    UserRoleDTO create(UserRoleDTO userRoleDTO);
    UserRoleDTO update(Integer id, UserRoleDTO userRoleDTO);
    void delete(Integer id);
}
