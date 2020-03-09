package com.ekin.shopping_cart.repository;

import com.ekin.shopping_cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCartId(Long id);
    CartItem findByCartIdAndProductId(Long cartId,Long productId);
}
