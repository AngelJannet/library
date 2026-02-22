package com.example.library.dto.auth;

public class RegisterRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public RegisterRequestDto() {}

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}


