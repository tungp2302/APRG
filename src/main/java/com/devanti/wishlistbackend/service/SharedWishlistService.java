package com.devanti.wishlistbackend.service;

import com.devanti.wishlistbackend.model.SharedWishlist;
import com.devanti.wishlistbackend.model.User;
import com.devanti.wishlistbackend.repository.SharedWishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharedWishlistService {

    private final SharedWishlistRepository sharedWishlistRepository;

    public SharedWishlistService(SharedWishlistRepository sharedWishlistRepository) {
        this.sharedWishlistRepository = sharedWishlistRepository;
    }

    public void shareWishlist(User owner, String recipientEmail) {
        SharedWishlist sharedWishlist = new SharedWishlist();
        sharedWishlist.setOwner(owner);
        sharedWishlist.setRecipientEmail(recipientEmail);
        sharedWishlistRepository.save(sharedWishlist);
    }

    public List<SharedWishlist> getSharedWishlists(Long ownerId) {
        return sharedWishlistRepository.findByOwnerId(ownerId);
    }
}
