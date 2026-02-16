package com.example.library.service;

import com.example.library.entity.Role;
import com.example.library.entity.User;
import com.example.library.repository.RoleRepository;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User registerUser (String firstName, String lastName, String email, String password){
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(()-> new RuntimeException("Error: Role is not found."));

        User user = new User(firstName, lastName, email, password);
        user.getRoles().add(userRole);

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public void addRoleToUser(String email, String roleName) {
        User user = findByEmail(email);
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
    }
}
