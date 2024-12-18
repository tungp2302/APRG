package com.devanti.wishlistbackend.controller;

import com.devanti.wishlistbackend.service.WishlistService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {

    private final WishlistService wishlistService;

    public LandingController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/")
    public String showLandingPage(Model model) {
        // Beispielhafte Items für die Landingpage
        model.addAttribute("publicItems", wishlistService.getPublicWishlistItems());

        // Benutzer-Authentifizierungsstatus prüfen
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = authentication != null && authentication.isAuthenticated() &&
                !authentication.getName().equals("anonymousUser");

        model.addAttribute("isLoggedIn", isLoggedIn);
        return "landing";
    }
}

