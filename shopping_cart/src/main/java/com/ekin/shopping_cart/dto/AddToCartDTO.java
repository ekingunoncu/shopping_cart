package com.ekin.shopping_cart.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AddToCartDTO implements Serializable {

    @NotNull(message = "productId id is required")
    private Long productId;
    @NotNull(message = "quantity id is required")
    private Integer quantity;
    @NotNull(message = "userId id is required")
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
