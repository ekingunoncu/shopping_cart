package com.ekin.shopping_cart.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.ekin.shopping_cart.dto.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


public class CartControllerTest extends BaseControllerTest {

    public static Long cartId;

    public static Long cartItemId;

    @Test
    public void create() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/cart/");
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(9898L);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<CartDTO> request = new HttpEntity<>(cartDTO, headers);

        ResponseEntity<CartDTO> result = this.restTemplate.postForEntity(uri, request, CartDTO.class);

        Assert.assertNotNull(result.getBody().getCartId());
        cartId = result.getBody().getCartId();
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void delete() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/cart");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity request = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri.toString())
                .queryParam("id", String.valueOf(cartId));

        ResponseEntity<String> result = this.restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, request, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void addToCart() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/cart/addToCart");
        AddToCartDTO addToCartDTO = new AddToCartDTO();
        addToCartDTO.setProductId(38L);
        addToCartDTO.setQuantity(5);
        addToCartDTO.setUserId(1L);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<AddToCartDTO> request = new HttpEntity<>(addToCartDTO, headers);
        ResponseEntity<CartItemDTO> result = this.restTemplate.postForEntity(uri, request, CartItemDTO.class);
        cartItemId = result.getBody().getCartItemId();
        Assert.assertNotNull(result.getBody().getCartItemId());
    }

    @Test
    public void deleteCartItem() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/cart/deleteCartItem");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity request = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri.toString())
                .queryParam("id", cartItemId);

        ResponseEntity<String> result = this.restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, request, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void getCartsInformationWithoutCoupon() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/cart/getCartsInformation");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity request = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri.toString())
                .queryParam("userId", 1L);

        ResponseEntity<CartResultDTO> result = this.restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, CartResultDTO.class);

        CartResultDTO cartResultDTO = result.getBody();
        BigDecimal controlPrice = new BigDecimal(0);
        if (cartResultDTO != null) {
            List<ProductDTO> products = cartResultDTO.getProducts();
            if (products.size() > 0) {
                for (ProductDTO product : products) {
                    controlPrice = controlPrice.add(product.getPrice().multiply(new BigDecimal(product.getQuantity())));
                }
                Assert.assertTrue(controlPrice.equals(cartResultDTO.getTotalPrice()));
            }
        }
        Assert.assertNotNull(cartResultDTO.getLastPrice());
    }
}