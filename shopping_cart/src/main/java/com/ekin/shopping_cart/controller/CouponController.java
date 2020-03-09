package com.ekin.shopping_cart.controller;

import com.ekin.shopping_cart.dto.CouponDTO;
import com.ekin.shopping_cart.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/coupon")
public class CouponController {

    private CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<CouponDTO> create(@Valid @RequestBody CouponDTO couponDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(couponService.create(couponDTO));
    }

    @DeleteMapping
    public @ResponseBody ResponseEntity delete(@RequestParam Long id){
        couponService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
