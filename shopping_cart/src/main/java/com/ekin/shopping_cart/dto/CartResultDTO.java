package com.ekin.shopping_cart.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class CartResultDTO {
    private List<ProductDTO> products;
    private List<Map<String,Object>> discounts;
    private Long couponId;
    private Boolean isCouponUsed;
    private Long cartId;
    private BigDecimal lastPrice;
    private BigDecimal discount;
    private BigDecimal totalPrice;
    private BigDecimal deliveryCost;

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public List<Map<String, Object>> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Map<String, Object>> discounts) {
        this.discounts = discounts;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Boolean getCouponUsed() {
        return isCouponUsed;
    }

    public void setCouponUsed(Boolean couponUsed) {
        isCouponUsed = couponUsed;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
