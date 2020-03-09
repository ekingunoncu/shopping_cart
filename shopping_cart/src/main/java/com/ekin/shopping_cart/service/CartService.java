package com.ekin.shopping_cart.service;

import com.ekin.shopping_cart.dto.*;

public interface CartService {
    CartDTO create(CartDTO cartDTO) throws Exception;

    CartItemDTO addToCart(AddToCartDTO addToCartDTO);

    void delete(Long id);

    CartResultDTO getCartsInformation(Long couponId, Long userId) throws Exception;
}
