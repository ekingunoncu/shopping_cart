package com.ekin.shopping_cart.service;

import com.ekin.shopping_cart.dto.CartResultDTO;
import com.ekin.shopping_cart.dto.ProductDTO;
import com.ekin.shopping_cart.entity.Cart;
import com.ekin.shopping_cart.entity.CartItem;
import com.ekin.shopping_cart.entity.Product;
import com.ekin.shopping_cart.repository.CartItemRepository;
import com.ekin.shopping_cart.repository.CartRepository;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ekin.shopping_cart.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class CartServiceImplTest {

    @Mock
    CartRepository cartRepository;

    @Mock
    CartItemRepository cartItemRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    CartServiceImpl cartService;


    @Test
    void getCartsInformation() {
        MockitoAnnotations.initMocks(this);

        Cart _cart =  new Cart();
        _cart.setUserId(5L);
        _cart.setCartId(6L);
        when(cartRepository.findByUserId(5L)).thenReturn(_cart);
        Cart cart = cartRepository.findByUserId(5L);
        List<CartItem> _items = new ArrayList<>();

        CartItem item1 = new CartItem();
        item1.setPrice(new BigDecimal(5));
        item1.setQuantity(6);
        item1.setCartId(5L);
        item1.setDiscount(new BigDecimal(25));
        item1.setProductId(32L);
        item1.setCategoryId(5L);
        item1.setCartItemId(4L);

        CartItem item2 = new CartItem();
        item2.setPrice(new BigDecimal(5));
        item2.setQuantity(6);
        item2.setCartId(5L);
        item2.setDiscount(new BigDecimal(25));
        item2.setProductId(30L);
        item2.setCategoryId(5L);
        item2.setCartItemId(4L);

        _items.add(item1);
        _items.add(item2);

        when(cartItemRepository.findAllByCartId(5L)).thenReturn(_items);

        Product product1 =  new Product();
        product1.setTitle("uzay gemisi");
        product1.setProductId(3L);
        product1.setCategoryId(5L);
        product1.setPrice(new BigDecimal(20));

        Product product2 =  new Product();
        product2.setTitle("ışın kılıcı");
        product2.setProductId(3L);
        product2.setCategoryId(5L);
        product2.setPrice(new BigDecimal(20));

        when(productRepository.findById(30L)).thenReturn(java.util.Optional.of(product2));
        when(productRepository.findById(32L)).thenReturn(java.util.Optional.of(product1));

        List<CartItem> items = cartItemRepository.findAllByCartId(cart.getCartId());
        CartResultDTO cartResultDTO = new CartResultDTO();
        List<ProductDTO> products = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal lastPrice = new BigDecimal(0);
        BigDecimal discount = new BigDecimal(0);

        if (cart != null && items.size() > 0) {
            for (CartItem item : items) {
                totalPrice = totalPrice.add(item.getPrice());
                productRepository.findById(item.getProductId()).ifPresent(product -> {
                    ProductDTO productDTO = ProductDTO.toDTO(product);
                    productDTO.setQuantity(item.getQuantity());
                    products.add(productDTO);
                });
            }

            cartResultDTO.setTotalPrice(totalPrice);
            discount = totalPrice.subtract(lastPrice);
            cartResultDTO.setCartId(cart.getCartId());
            cartResultDTO.setProducts(products);
            cartResultDTO.setDiscount(discount);
            cartResultDTO.setLastPrice(lastPrice);
        }
    }
}