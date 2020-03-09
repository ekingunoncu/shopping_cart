package com.ekin.shopping_cart.service;


import com.ekin.shopping_cart.dto.CartItemDTO;

public interface CartItemService {
    CartItemDTO create(CartItemDTO cartItemDTO);
    void delete(Long id);
}
