package com.ekin.shopping_cart.controller;

import com.ekin.shopping_cart.dto.CategoryDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;


public class CategoryControllerTest extends BaseControllerTest {

    public static Long categoryId;

    @org.junit.Test
    public void create() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/category/");
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setTitle("ışın kılıcıçları");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<CategoryDTO> request = new HttpEntity<>(categoryDTO, headers);

        ResponseEntity<CategoryDTO> result = this.restTemplate.postForEntity(uri, request, CategoryDTO.class);

        Assert.assertNotNull(result.getBody().getCategoryId());
        categoryId = result.getBody().getCategoryId();
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void delete() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/category/");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity request = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri.toString())
                .queryParam("id", String.valueOf(categoryId));

        ResponseEntity<String> result = this.restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, request, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}