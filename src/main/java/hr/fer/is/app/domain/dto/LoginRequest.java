package hr.fer.is.app.domain.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}