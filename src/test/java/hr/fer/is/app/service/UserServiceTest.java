package hr.fer.is.app.service;

import hr.fer.is.app.domain.User;
import hr.fer.is.app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.UUID;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user = new User();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        User user = new User();
        UUID id = UUID.randomUUID();
        user.setId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(id);

        assertEquals(user, result.get());
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testGetUserByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(id);

        assertEquals(Optional.empty(), result);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testGetUserByEmail() {
        User user = new User();
        String email = "test@email.com";
        user.setEmail(email);
        when(userRepository.findOneByEmailIgnoreCase(email)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByEmail(email);

        assertEquals(user, result.get());
        verify(userRepository, times(1)).findOneByEmailIgnoreCase(email);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        UUID id = UUID.randomUUID();
        user.setId(id);
        when(userRepository.existsById(id)).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.updateUser(user);

        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUserNotFound() {
        User user = new User();
        UUID id = UUID.randomUUID();
        user.setId(id);
        when(userRepository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userService.updateUser(user));
        verify(userRepository, times(0)).save(user);
    }

    @Test
    void testDeleteUser() {
        UUID id = UUID.randomUUID();
        when(userRepository.existsById(id)).thenReturn(true);

        userService.deleteUser(id);

        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteUserNotFound() {
        UUID id = UUID.randomUUID();
        when(userRepository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(id));
        verify(userRepository, times(0)).deleteById(id);
    }

}
