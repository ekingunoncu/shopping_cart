package com.ekin.shopping_cart.controller;

import java.net.URI;
import java.net.URISyntaxException;

import com.ekin.shopping_cart.dto.CampaignDTO;
import com.ekin.shopping_cart.enums.DiscountType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;


public class CampaignControllerTest extends BaseControllerTest {

    public static Long campaignId;

    @Test
    public void create() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/campaign/");
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setCategoryId(33L);
        campaignDTO.setMinimumQuantity(5);
        campaignDTO.setDiscount(new BigDecimal(5));
        campaignDTO.setDiscountType(DiscountType.AMOUNT);


        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<CampaignDTO> request = new HttpEntity<>(campaignDTO, headers);

        ResponseEntity<CampaignDTO> result = this.restTemplate.postForEntity(uri, request, CampaignDTO.class);

        Assert.assertNotNull(result.getBody().getCampaignId());
        campaignId = result.getBody().getCampaignId();
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void delete() throws URISyntaxException {
        URI uri = new URI(baseUrl + "/campaign/");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity request = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri.toString())
                .queryParam("id", String.valueOf(campaignId));

        ResponseEntity<String> result = this.restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, request, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}