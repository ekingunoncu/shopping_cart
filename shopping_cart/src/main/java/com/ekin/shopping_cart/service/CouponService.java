package com.ekin.shopping_cart.service;


import com.ekin.shopping_cart.dto.CouponDTO;

public interface CouponService {
    CouponDTO create(CouponDTO couponDTO);
    void delete(Long id);
}
