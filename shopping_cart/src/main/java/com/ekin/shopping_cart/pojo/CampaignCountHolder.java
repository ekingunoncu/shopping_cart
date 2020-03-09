package com.ekin.shopping_cart.pojo;

import com.ekin.shopping_cart.entity.Campaign;

public class CampaignCountHolder {
    private Campaign campaign;
    private Integer totalQuantity;

    public CampaignCountHolder(Campaign campaign, Integer totalQuantity) {
        this.campaign = campaign;
        this.totalQuantity = totalQuantity;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
