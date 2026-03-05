package com.example.library.service;

import com.example.library.entity.Role;
import com.example.library.entity.User;
import com.example.library.repository.RoleRepository;
import com.example.library.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String firstName,
                             String lastName,
                             String email,
                             String password) {

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() ->
                        new RuntimeException("Error: Role USER is not found."));

        User user = new User(
                firstName,
                lastName,
                email,
                passwordEncoder.encode(password)
        );

        user.getRoles().add(userRole);

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found with email: " + email));
    }

    @Transactional
    public void addRoleToUser(String email, String roleName) {
        User user = findByEmail(email);

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() ->
                        new RuntimeException("Role not found: " + roleName));

        user.getRoles().add(role);
    }
}