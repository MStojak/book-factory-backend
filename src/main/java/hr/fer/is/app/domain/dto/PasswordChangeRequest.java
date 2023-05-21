package hr.fer.is.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PasswordChangeRequest {
    private String oldPassword;
    private String newPassword;
}