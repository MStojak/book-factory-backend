package hr.fer.is.app.domain.dto;


import hr.fer.is.app.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String email;

    public UserDTO(final User user) {
        this.email = user.getEmail();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "email='" + email + '\'' +
            "}";
    }
}
