package com.ekin.shopping_cart.repository;

import com.ekin.shopping_cart.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign,Long> {
    Campaign findByCategoryId(Long id);
}
