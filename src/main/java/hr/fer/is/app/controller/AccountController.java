package hr.fer.is.app.controller;

import hr.fer.is.app.domain.User;
import hr.fer.is.app.domain.dto.PasswordChangeRequest;
import hr.fer.is.app.domain.dto.UserRegistrationRequest;
import hr.fer.is.app.service.UserRoleService;
import hr.fer.is.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService roleService;

    @Autowired
    public AccountController(UserService userService, PasswordEncoder passwordEncoder, final UserRoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        // Check if email is already in use
        if (userService.getUserByEmail(registrationRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Create new user
        User newUser = new User();
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setFirstName(registrationRequest.getFirstName());
        newUser.setLastName(registrationRequest.getLastName());
        newUser.setPasswordHash(passwordEncoder.encode(registrationRequest.getPassword()));
        newUser.setRetired(false);
        newUser.setRole(roleService.getRawById(2));
        userService.createUser(newUser);

        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest, Principal principal) {
        Optional<User> user = userService.getUserByEmail(principal.getName());

        if (user.isPresent()) {
            if (!passwordEncoder.matches(passwordChangeRequest.getOldPassword(), user.get().getPasswordHash())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            user.get().setPasswordHash(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
            userService.updateUser(user.get());

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
