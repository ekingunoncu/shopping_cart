package com.ekin.shopping_cart.entity;

import javax.persistence.*;

@Entity
@Table(name = "CART_TABLE")
public class Cart {

    @Id
    @Column(name = "CART_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @Column(name = "USER_ID")
    private Long userId;

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
