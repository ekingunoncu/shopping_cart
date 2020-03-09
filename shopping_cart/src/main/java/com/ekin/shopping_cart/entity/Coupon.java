package com.ekin.shopping_cart.entity;

import com.ekin.shopping_cart.enums.DiscountType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "COUPON_TABLE")
public class Coupon {

    @Id
    @Column(name = "COUPON_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long couponId;

    @Column(name = "MINIMUM_AMOUNT")
    private BigDecimal minimumAmount;

    @Column(name = "DISCOUNT")
    private BigDecimal discount;

    @Enumerated(EnumType.STRING)
    @Column(name = "DISCOUNT_TYPE", length = 10)
    private DiscountType discountType;

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(BigDecimal minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }
}
