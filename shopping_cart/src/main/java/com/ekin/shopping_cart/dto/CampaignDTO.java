package com.ekin.shopping_cart.dto;

import com.ekin.shopping_cart.entity.Campaign;
import com.ekin.shopping_cart.enums.DiscountType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class CampaignDTO implements Serializable {

    private Long campaignId;
    @NotNull(message = "discount field can not be null.")
    private BigDecimal discount;
    @NotNull(message = "categoryId field can not be null.")
    private Long categoryId;
    private Integer minimumQuantity;
    @NotNull(message = "discountType field can not be null.")
    private DiscountType discountType;

    public Campaign toEntity() {
        Campaign campaign = new Campaign();
        campaign.setCampaignId(this.campaignId);
        campaign.setCategoryId(this.categoryId);
        campaign.setDiscount(this.discount);
        campaign.setDiscountType(this.discountType);
        campaign.setMinimumQuantity(this.minimumQuantity);
        return campaign;
    }

    public static CampaignDTO toDTO(Campaign campaign) {
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setCategoryId(campaign.getCategoryId());
        campaignDTO.setCampaignId(campaign.getCampaignId());
        campaignDTO.setDiscount(campaign.getDiscount());
        campaignDTO.setDiscountType(campaign.getDiscountType());
        campaignDTO.setMinimumQuantity(campaign.getMinimumQuantity());
        return campaignDTO;
    }

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
