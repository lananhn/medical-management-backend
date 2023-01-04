package com.medicalmanagement.services.dto.response;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";

    public LoginResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
