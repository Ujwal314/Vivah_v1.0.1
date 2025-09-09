package com.example.dto;

import lombok.Getter;

@Getter
public class AuthResponse {

    // Getters & Setters
    private String token;
    private String message;

    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}