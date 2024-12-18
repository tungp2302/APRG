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
        return "register"; // Zeigt die Registrierungsseite an
    }

    @PostMapping("/register")
    public String handleRegister(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.registerUser(username, password)) {
            // Benutzer nach erfolgreicher Registrierung automatisch einloggen
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(token);

            return "redirect:/wishlist"; // Weiterleitung zur Wunschliste nach der Registrierung
        } else {
            model.addAttribute("error", "Der Benutzername existiert bereits!");
            return "register"; // Bleibt auf der Registrierungsseite, wenn der Benutzername existiert
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Zeigt die Login-Seite an
    }
}

