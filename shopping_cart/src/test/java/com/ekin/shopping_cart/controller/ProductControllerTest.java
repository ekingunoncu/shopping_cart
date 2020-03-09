package com.ekin.shopping_cart.controller;

import java.net.URI;
import java.net.URISyntaxException;

import com.ekin.shopping_cart.dto.ProductDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;


public class ProductControllerTest extends BaseControllerTest {

    public static Long productId;

    @Test
    public void create() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/product/");
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategoryId(33L);
        productDTO.setPrice(new BigDecimal(15));
        productDTO.setTitle("ışın kılıcı");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO, headers);

        ResponseEntity<ProductDTO> result = this.restTemplate.postForEntity(uri, request, ProductDTO.class);

        Assert.assertNotNull(result.getBody().getProductId());
        productId = result.getBody().getProductId();
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void delete() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/product/");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity request = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri.toString())
                .queryParam("id", String.valueOf(productId));

        ResponseEntity<String> result = this.restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, request, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}