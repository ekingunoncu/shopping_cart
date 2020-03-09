package com.ekin.shopping_cart.calculator;

import com.ekin.shopping_cart.entity.CartItem;
import com.ekin.shopping_cart.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DeliveryCostCalculator {

    @Value("${delivery.fixedCost}")
    public BigDecimal fixedCost;

    @Value("${delivery.costPerProduct}")
    public BigDecimal costPerProduct;

    @Value("${delivery.costPerDelivery}")
    public BigDecimal costPerDelivery;

    private CartItemRepository cartItemRepository;

    public DeliveryCostCalculator(CartItemRepository cartItemRepository){
        this.cartItemRepository = cartItemRepository;
    }

    public BigDecimal calculateDeliveryCost(Long cartId){

        Map<Long,Object> distinctCategoriesMap = new HashMap<>();
        List<CartItem> cartItemList = cartItemRepository.findAllByCartId(cartId);
        for(CartItem cartItem:cartItemList){
            distinctCategoriesMap.put(cartItem.getCategoryId(),cartItem.getCartId());
        }

        BigDecimal numberOfProducts = new BigDecimal(cartItemList.size());
        //number of deliveries
        BigDecimal distinctCategories = new BigDecimal(distinctCategoriesMap.values().size());

        return distinctCategories.multiply(costPerDelivery).add(numberOfProducts.multiply(costPerProduct).add(fixedCost));
    }


}
