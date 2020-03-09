package com.ekin.shopping_cart.dto;

import com.ekin.shopping_cart.entity.CartItem;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class CartItemDTO implements Serializable {

    private Long cartItemId;
    @NotNull(message = "cartId field can not be null.")
    private Long cartId;
    @NotNull(message = "productId field can not be null.")
    private Long productId;
    @NotEmpty(message = "quantity field can not be null.")
    private Integer quantity;
    @NotNull(message = "discount field can not be null.")
    private BigDecimal discount;
    @NotNull(message = "price field can not be null.")
    private BigDecimal price;

    public CartItem toEntity(){
        CartItem cartItem = new CartItem();
        cartItem.setCartId(this.getCartId());
        cartItem.setCartItemId(this.getCartItemId());
        cartItem.setProductId(this.getProductId());
        cartItem.setQuantity(this.getQuantity());
        cartItem.setDiscount(this.getDiscount());
        cartItem.setPrice(this.getPrice());
        return cartItem;
    }

    public static CartItemDTO toDTO(CartItem cartItem){
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setPrice(cartItem.getPrice());
        cartItemDTO.setCartItemId(cartItem.getCartItemId());
        cartItemDTO.setCartId(cartItem.getCartId());
        cartItemDTO.setProductId(cartItem.getProductId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setDiscount(cartItem.getDiscount());
        return cartItemDTO;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
