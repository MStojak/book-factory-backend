package hr.fer.is.app.domain.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    public LoginResponse(final String token) {
        this.token = token;
    }
}