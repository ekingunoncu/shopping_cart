package com.ekin.shopping_cart.repository;

import com.ekin.shopping_cart.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Coupon findByCouponId(Long id);
}
