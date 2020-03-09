package com.ekin.shopping_cart.service;

import com.ekin.shopping_cart.dto.CampaignDTO;
import com.ekin.shopping_cart.repository.CampaignRepository;
import org.springframework.stereotype.Service;

@Service("campaignService")
public class CampaignServiceImpl implements CampaignService {

    private CampaignRepository campaignRepository;

    CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public CampaignDTO create(CampaignDTO campaignDTO) throws Exception {
        if (!campaignRepository.findById(campaignDTO.getCategoryId()).isPresent()) {
            return CampaignDTO.toDTO(campaignRepository.save(campaignDTO.toEntity()));
        } else {
            throw new Exception("Category already has a campaign " + campaignDTO.getCategoryId());
        }
    }

    @Override
    public void delete(Long id) {
        campaignRepository.deleteById(id);
    }
}
