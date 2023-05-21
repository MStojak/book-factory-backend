package hr.fer.is.app.service.impl;

import hr.fer.is.app.domain.User;
import hr.fer.is.app.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // find user by username
        Optional<User> user = userRepository.findOneByEmailIgnoreCase(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        // return user details
        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                user.get().getPasswordHash(),
                new ArrayList<>()); // roles can be added here
    }
}
