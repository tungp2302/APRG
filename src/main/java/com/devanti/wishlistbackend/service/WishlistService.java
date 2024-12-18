package com.devanti.wishlistbackend.service;

import com.devanti.wishlistbackend.model.User;
import com.devanti.wishlistbackend.model.WishlistItem;
import com.devanti.wishlistbackend.repository.WishlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Transactional
    public void addItemToWishlist(User user, String itemName) {
        WishlistItem item = new WishlistItem();
        item.setUser(user); // Der Benutzer ist jetzt korrekt mit der Datenbank verknüpft
        item.setItemName(itemName); // Wunsch hinzufügen
        wishlistRepository.save(item); // In Datenbank speichern
    }

    public List<WishlistItem> getUserWishlist(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public void removeItemFromWishlist(Long itemId) {
        wishlistRepository.deleteById(itemId);
    }
}
