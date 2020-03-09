package com.ekin.shopping_cart.dto;
import com.ekin.shopping_cart.entity.Cart;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CartDTO implements Serializable {

    private Long cartId;
    @NotNull(message = "User id cannot be null")
    private Long userId;

    public Cart toEntity() {
        Cart cart = new Cart();
        cart.setCartId(this.cartId);
        cart.setUserId(this.userId);
        return cart;
    }

    public static CartDTO toDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setUserId(cart.getUserId());
        return cartDTO;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
