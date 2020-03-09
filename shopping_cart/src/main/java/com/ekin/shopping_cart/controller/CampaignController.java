package com.ekin.shopping_cart.controller;

import com.ekin.shopping_cart.dto.CampaignDTO;
import com.ekin.shopping_cart.service.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="api/campaign")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService){
        this.campaignService = campaignService;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<CampaignDTO> create(@Valid @RequestBody CampaignDTO campaignDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(campaignService.create(campaignDTO));
    }

    @DeleteMapping
    public @ResponseBody ResponseEntity delete(@RequestParam Long id){
        campaignService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
