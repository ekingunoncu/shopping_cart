package com.ekin.shopping_cart.service;

import com.ekin.shopping_cart.dto.CouponDTO;
import com.ekin.shopping_cart.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {

    CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public CouponDTO create(CouponDTO couponDTO) {
        return CouponDTO.toDTO(couponRepository.save(couponDTO.toEntity()));
    }

    @Override
    public void delete(Long id) {
        couponRepository.deleteById(id);
    }
}
