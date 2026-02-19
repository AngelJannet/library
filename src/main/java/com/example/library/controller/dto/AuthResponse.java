package com.example.library.controller.dto;

import java.util.Set;

public class AuthResponse {

    private final String token;
    private final Set<String> roles;

    public AuthResponse(String token, Set<String> roles) {
        this.token = token;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public Set<String> getRoles() {
        return roles;
    }
}

