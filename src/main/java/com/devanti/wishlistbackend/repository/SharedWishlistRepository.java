package com.devanti.wishlistbackend.repository;

import com.devanti.wishlistbackend.model.SharedWishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SharedWishlistRepository extends JpaRepository<SharedWishlist, Long> {
    List<SharedWishlist> findByOwnerId(Long ownerId); // Alle geteilten Wunschlisten eines Benutzers
}
