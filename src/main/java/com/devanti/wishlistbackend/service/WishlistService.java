package com.devanti.wishlistbackend.service;

import com.devanti.wishlistbackend.model.User;
import com.devanti.wishlistbackend.model.WishlistItem;
import com.devanti.wishlistbackend.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public void addItemToWishlist(User user, String itemName) {
        WishlistItem item = new WishlistItem();
        item.setUser(user);
        item.setItemName(itemName);
        wishlistRepository.save(item);
    }

    public List<WishlistItem> getUserWishlist(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public void removeItemFromWishlist(Long itemId) {
        wishlistRepository.deleteById(itemId);
    }
}
