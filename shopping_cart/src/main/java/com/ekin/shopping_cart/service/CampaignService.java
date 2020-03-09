package com.ekin.shopping_cart.service;

import com.ekin.shopping_cart.dto.CampaignDTO;

public interface CampaignService {
    CampaignDTO create(CampaignDTO campaignDTO) throws Exception;
    void delete(Long id);
}
