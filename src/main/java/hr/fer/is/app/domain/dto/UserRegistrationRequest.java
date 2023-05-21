package hr.fer.is.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserRegistrationRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}