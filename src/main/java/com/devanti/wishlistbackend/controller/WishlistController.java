package com.devanti.wishlistbackend.controller;

import com.devanti.wishlistbackend.model.User;
import com.devanti.wishlistbackend.service.WishlistService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public String getWishlist(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model) {
        // Aktuellen Benutzer identifizieren
        User user = new User();
        user.setUsername(userDetails.getUsername());

        // Wunschliste abrufen
        model.addAttribute("wishlist", wishlistService.getUserWishlist(user.getId()));
        return "wishlist"; // Zeigt die Wunschliste an
    }

    @PostMapping("/add")
    public String addItem(@RequestParam String itemName, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());

        wishlistService.addItemToWishlist(user, itemName);
        return "redirect:/wishlist";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam Long itemId) {
        wishlistService.removeItemFromWishlist(itemId);
        return "redirect:/wishlist";
    }
}
