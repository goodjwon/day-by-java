package com.example.ch6.spring.security.service;

import com.example.ch6.spring.security.model.User;
import com.example.ch6.spring.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(username, encodedPassword, "ROLE_USER");
        return userRepository.save(newUser);
    }
}
