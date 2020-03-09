package com.ekin.shopping_cart.entity;

import com.ekin.shopping_cart.enums.DiscountType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CAMPAIGN_TABLE")
public class Campaign {

    @Id
    @Column(name = "CAMPAIGN_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long campaignId;

    @Column(name = "DISCOUNT")
    private BigDecimal discount;

    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "MINIMUM_QUANTITY")
    private Integer minimumQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "DISCOUNT_TYPE", length = 10)
    private DiscountType discountType;

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }
}
