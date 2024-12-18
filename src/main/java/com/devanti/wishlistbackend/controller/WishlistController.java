package com.devanti.wishlistbackend.controller;

import com.devanti.wishlistbackend.model.User;
import com.devanti.wishlistbackend.service.WishlistService;
import com.devanti.wishlistbackend.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;
    private final UserRepository userRepository; // UserRepository hinzufügen

    // Konstruktor-Injektion
    public WishlistController(WishlistService wishlistService, UserRepository userRepository) {
        this.wishlistService = wishlistService;
        this.userRepository = userRepository; // UserRepository initialisieren
    }

    @GetMapping
    public String getWishlist(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model) {
        // Benutzer aus der Datenbank laden
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden: " + userDetails.getUsername()));

        // Wunschliste abrufen
        model.addAttribute("wishlist", wishlistService.getUserWishlist(user.getId()));
        return "wishlist";
    }

    @PostMapping("/add")
    public String addItem(@RequestParam String itemName, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        // Benutzer aus der Datenbank laden
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden: " + userDetails.getUsername()));

        // Wunsch hinzufügen
        wishlistService.addItemToWishlist(user, itemName);

        // Nach dem Hinzufügen zurück zur Wunschliste weiterleiten
        return "redirect:/wishlist";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam Long itemId) {
        wishlistService.removeItemFromWishlist(itemId);
        return "redirect:/wishlist";
    }
}
