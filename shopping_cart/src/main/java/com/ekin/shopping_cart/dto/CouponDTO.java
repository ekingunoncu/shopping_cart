package com.ekin.shopping_cart.dto;

import com.ekin.shopping_cart.entity.Coupon;
import com.ekin.shopping_cart.enums.DiscountType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class CouponDTO implements Serializable {

    private Long couponId;
    @NotNull(message = "minimumAmount field can not be null.")
    private BigDecimal minimumAmount;
    @NotNull(message = "discount field can not be null.")
    private BigDecimal discount;
    @NotNull(message = "discountType field can not be null.")
    private DiscountType discountType;

    public Coupon toEntity() {
        Coupon coupon = new Coupon();
        coupon.setMinimumAmount(this.minimumAmount);
        coupon.setDiscountType(this.discountType);
        coupon.setDiscount(this.discount);
        coupon.setCouponId(this.couponId);
        return coupon;
    }

    public static CouponDTO toDTO(Coupon coupon) {
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setCouponId(coupon.getCouponId());
        couponDTO.setDiscount(coupon.getDiscount());
        couponDTO.setDiscountType(coupon.getDiscountType());
        couponDTO.setMinimumAmount(coupon.getMinimumAmount());
        return couponDTO;
    }

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
