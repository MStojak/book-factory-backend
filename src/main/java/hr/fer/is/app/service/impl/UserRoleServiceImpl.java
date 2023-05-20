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

/**
 * Service Implementation for managing {@link UserRole}.
 */
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final Logger log = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    private final UserRoleRepository userRoleRepository;

    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRoleMapper userRoleMapper) {
        this.userRoleRepository = userRoleRepository;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public Mono<UserRoleDTO> save(UserRoleDTO userRoleDTO) {
        log.debug("Request to save UserRole : {}", userRoleDTO);
        return userRoleRepository.save(userRoleMapper.toEntity(userRoleDTO)).map(userRoleMapper::toDto);
    }

    @Override
    public Mono<UserRoleDTO> update(UserRoleDTO userRoleDTO) {
        log.debug("Request to update UserRole : {}", userRoleDTO);
        return userRoleRepository.save(userRoleMapper.toEntity(userRoleDTO)).map(userRoleMapper::toDto);
    }

    @Override
    public Mono<UserRoleDTO> partialUpdate(UserRoleDTO userRoleDTO) {
        log.debug("Request to partially update UserRole : {}", userRoleDTO);

        return userRoleRepository
            .findById(userRoleDTO.getId())
            .map(existingUserRole -> {
                userRoleMapper.partialUpdate(existingUserRole, userRoleDTO);

                return existingUserRole;
            })
            .flatMap(userRoleRepository::save)
            .map(userRoleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<UserRoleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserRoles");
        return userRoleRepository.findAllBy(pageable).map(userRoleMapper::toDto);
    }

    public Mono<Long> countAll() {
        return userRoleRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<UserRoleDTO> findOne(Long id) {
        log.debug("Request to get UserRole : {}", id);
        return userRoleRepository.findById(id).map(userRoleMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete UserRole : {}", id);
        return userRoleRepository.deleteById(id);
    }
}
