package com.example.library.dto.auth;

import java.util.Set;

public class AuthResponseDto {

    private String token;
    private Set<String> roles;

    public AuthResponseDto(String token, Set<String> roles) {
        this.token = token;
        this.roles = roles;
    }

    public String getToken() { return token; }
    public Set<String> getRoles() { return roles; }
}
