package com.devanti.wishlistbackend.controller;

import com.devanti.wishlistbackend.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Returns the registration page
    }

    @PostMapping("/register")
    public String handleRegister(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.registerUser(username, password)) {
            // Auto-login the user after successful registration
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(token);

            return "redirect:/"; // Redirect to the home page after successful registration
        } else {
            model.addAttribute("error", "Username already exists!");
            return "register"; // Stay on the registration page if username exists
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Returns the login page
    }
}
