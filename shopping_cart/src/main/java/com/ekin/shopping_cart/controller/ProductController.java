package com.ekin.shopping_cart.controller;

import com.ekin.shopping_cart.dto.ProductDTO;
import com.ekin.shopping_cart.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDTO));
    }

    @DeleteMapping
    public @ResponseBody ResponseEntity delete(@RequestParam Long id){
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
