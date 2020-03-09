package com.ekin.shopping_cart.controller;

import com.ekin.shopping_cart.dto.*;
import com.ekin.shopping_cart.service.CartItemService;
import com.ekin.shopping_cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/cart")
public class CartController {

    private CartService cartService;
    private CartItemService cartItemService;

    public CartController(CartService cartService,CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<CartDTO> create(@Valid @RequestBody CartDTO cartDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.create(cartDTO));
    }

    @DeleteMapping
    public @ResponseBody ResponseEntity delete(@RequestParam Long id){
        cartService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/addToCart")
    public @ResponseBody
    ResponseEntity<CartItemDTO> addToCart(@RequestBody AddToCartDTO addToCartDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addToCart(addToCartDTO));
    }

    @GetMapping(value = "/getCartsInformation")
    public @ResponseBody
    ResponseEntity<CartResultDTO> getCartsInformation(@RequestParam @Nullable Long couponId, @RequestParam Long userId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartsInformation(couponId, userId));
    }

    @DeleteMapping("/deleteCartItem")
    public @ResponseBody ResponseEntity deleteCartItem(@RequestParam Long id){
        cartItemService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
