package com.devanti.wishlistbackend.repository;

import com.devanti.wishlistbackend.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByUserId(Long userId);
}
