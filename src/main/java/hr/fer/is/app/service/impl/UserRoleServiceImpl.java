package hr.fer.is.app.service.impl;

import hr.fer.is.app.converter.UserRoleMapper;
import hr.fer.is.app.domain.UserRole;
import hr.fer.is.app.domain.dto.UserRoleDTO;
import hr.fer.is.app.repository.UserRoleRepository;
import hr.fer.is.app.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRoleMapper userRoleMapper) {
        this.userRoleRepository = userRoleRepository;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public UserRoleDTO getById(Integer id) {
        return userRoleRepository.findById(id)
                .map(userRoleMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("UserRole not found"));
    }

    @Override
    public UserRole getRawById(Integer id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserRole not found"));
    }

    @Override
    public UserRoleDTO create(UserRoleDTO userRoleDTO) {
        UserRole userRole = userRoleMapper.toEntity(userRoleDTO);
        UserRole savedUserRole = userRoleRepository.save(userRole);
        return userRoleMapper.toDto(savedUserRole);
    }

    @Override
    public UserRoleDTO update(Integer id, UserRoleDTO userRoleDTO) {
        UserRole existingUserRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserRole not found"));

        userRoleMapper.updateEntityFromDto(userRoleDTO, existingUserRole);
        UserRole updatedUserRole = userRoleRepository.save(existingUserRole);
        return userRoleMapper.toDto(updatedUserRole);
    }

    @Override
    public void delete(Integer id) {
        if (!userRoleRepository.existsById(id)) {
            throw new EntityNotFoundException("UserRole not found");
        }
        userRoleRepository.deleteById(id);
    }
}
