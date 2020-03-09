package com.ekin.shopping_cart.service;

import com.ekin.shopping_cart.dto.CartItemDTO;
import com.ekin.shopping_cart.repository.CartItemRepository;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {


    private CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItemDTO create(CartItemDTO cartItemDTO) {
        return CartItemDTO.toDTO(cartItemRepository.save(cartItemDTO.toEntity()));
    }

    @Override
    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
}
