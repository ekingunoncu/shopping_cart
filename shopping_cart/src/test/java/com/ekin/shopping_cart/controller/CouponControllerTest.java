package com.ekin.shopping_cart.controller;

import com.ekin.shopping_cart.dto.CouponDTO;
import com.ekin.shopping_cart.enums.DiscountType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;


public class CouponControllerTest extends BaseControllerTest {

    public static Long couponId;

    @org.junit.Test
    public void create() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/coupon/");
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setDiscount(new BigDecimal(25));
        couponDTO.setMinimumAmount(new BigDecimal(150));
        couponDTO.setDiscountType(DiscountType.RATE);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<CouponDTO> request = new HttpEntity<>(couponDTO, headers);

        ResponseEntity<CouponDTO> result = this.restTemplate.postForEntity(uri, request, CouponDTO.class);

        Assert.assertNotNull(result.getBody().getCouponId());
        couponId = result.getBody().getCouponId();
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void delete() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/coupon/");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity request = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri.toString())
                .queryParam("id", String.valueOf(couponId));

        ResponseEntity<String> result = this.restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, request, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}