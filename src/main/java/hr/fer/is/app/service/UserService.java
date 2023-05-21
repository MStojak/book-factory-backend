package hr.fer.is.app.service;

import hr.fer.is.app.domain.User;
import hr.fer.is.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findOneByEmailIgnoreCase(email);
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        // we should check if the user exists
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User with id " + user.getId() + " not found");
        }
    }

    @Transactional
    public void deleteUser(UUID id) {
        // we should check if the user exists
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }
}
