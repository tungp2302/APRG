package com.devanti.wishlistbackend.service;

import com.devanti.wishlistbackend.model.User;
import com.devanti.wishlistbackend.repository.UserRepository;
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

    public boolean registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return false; // Username already exists
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Encrypt password
        userRepository.save(user);
        return true;
    }
}

