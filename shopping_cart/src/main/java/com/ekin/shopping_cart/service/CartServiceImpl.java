package com.ekin.shopping_cart.service;

import com.ekin.shopping_cart.calculator.DeliveryCostCalculator;
import com.ekin.shopping_cart.dto.*;
import com.ekin.shopping_cart.entity.Campaign;
import com.ekin.shopping_cart.entity.Cart;
import com.ekin.shopping_cart.entity.CartItem;
import com.ekin.shopping_cart.entity.Coupon;
import com.ekin.shopping_cart.enums.DiscountType;
import com.ekin.shopping_cart.pojo.CampaignCountHolder;
import com.ekin.shopping_cart.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    private CartItemRepository cartItemRepository;

    private ProductRepository productRepository;

    private CampaignRepository campaignRepository;

    private CouponRepository couponRepository;

    private DeliveryCostCalculator deliveryCostCalculator;

    private Map<Long, CampaignCountHolder> campaignCountHolderMap = new HashMap<>();

    CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, CampaignRepository campaignRepository,CouponRepository couponRepository,DeliveryCostCalculator deliveryCostCalculator) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.campaignRepository = campaignRepository;
        this.couponRepository = couponRepository;
        this.deliveryCostCalculator=deliveryCostCalculator;
    }

    @Override
    public CartDTO create(CartDTO cartDTO) throws Exception {
        Cart cart = cartRepository.findByUserId(cartDTO.getUserId());
        if (cart == null) {
            return CartDTO.toDTO(cartRepository.save(cartDTO.toEntity()));
        } else {
            throw new Exception("This user already has a cart");
        }
    }

    public CartItemDTO addToCart(AddToCartDTO addToCartDTO) {
        CartItem cartItem = new CartItem();
        productRepository.findById(addToCartDTO.getProductId()).ifPresent(product -> {
            cartItem.setPrice(product.getPrice().multiply(new BigDecimal(addToCartDTO.getQuantity())));
            cartItem.setCategoryId(product.getCategoryId());
            cartItem.setQuantity(addToCartDTO.getQuantity());
            cartItem.setProductId(addToCartDTO.getProductId());
            Cart cart = cartRepository.findByUserId(addToCartDTO.getUserId());
            cartItem.setCartId(cart.getCartId());
        });
        return CartItemDTO.toDTO(checkIfAlreadyExistAndSave(cartItem));
    }

    private CartItem checkIfAlreadyExistAndSave(CartItem cartItem) {
        CartItem item = cartItemRepository.findByCartIdAndProductId(cartItem.getCartId(), cartItem.getProductId());
        if (item != null) {
            item.setQuantity(item.getQuantity() + cartItem.getQuantity());
            item.setPrice(item.getPrice().add(cartItem.getPrice()));
            return cartItemRepository.save(item);
        } else {
            return cartItemRepository.save(cartItem);
        }
    }

    public CartResultDTO getCartsInformation(Long couponId, Long userId) throws Exception {
        Cart cart = cartRepository.findByUserId(userId);
        List<CartItem> items = cartItemRepository.findAllByCartId(cart.getCartId());
        CartResultDTO cartResultDTO = new CartResultDTO();
        List<ProductDTO> products = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal lastPrice = new BigDecimal(0);
        BigDecimal discount = new BigDecimal(0);

        if (cart != null && items.size() > 0) {
            for (CartItem item : items) {
                checkIfHasCampaign(item);
                totalPrice = totalPrice.add(item.getPrice());
                productRepository.findById(item.getProductId()).ifPresent(product -> {
                    ProductDTO productDTO = ProductDTO.toDTO(product);
                    productDTO.setQuantity(item.getQuantity());
                    products.add(productDTO);
                });
            }

            cartResultDTO.setTotalPrice(totalPrice);
            lastPrice = checkIfHasDiscount(cartResultDTO);
            discount = totalPrice.subtract(lastPrice);
            cartResultDTO.setCartId(cart.getCartId());
            cartResultDTO.setProducts(products);
            cartResultDTO.setDiscount(discount);
            cartResultDTO.setLastPrice(lastPrice);
            cartResultDTO = isCouponValid(couponId, cartResultDTO);
            cartResultDTO.setDeliveryCost(deliveryCostCalculator.calculateDeliveryCost(cart.getCartId()));
        }
        return cartResultDTO;
    }


    private CartResultDTO isCouponValid(Long couponId, CartResultDTO cartResultDTO) throws Exception {
        if (couponId != null) {
            Coupon coupon = couponRepository.findByCouponId(couponId);
            this.checkMinimumAmountToApply(coupon,cartResultDTO);
        }
        return cartResultDTO;
    }

    private void checkMinimumAmountToApply(Coupon coupon,CartResultDTO cartResultDTO) throws Exception{
        if (coupon != null) {
            // checks if amount is enough to apply coupon
            if (coupon.getMinimumAmount().compareTo(cartResultDTO.getLastPrice()) == 1) {
                cartResultDTO.setCouponId(coupon.getCouponId());
                cartResultDTO.setCouponUsed(false);
            } else {
               this.applyCoupon(coupon,cartResultDTO);
            }
        } else {
            throw new Exception("coupon cannot find by couponId " + coupon.getCouponId());
        }
    }

    private void applyCoupon(Coupon coupon,CartResultDTO cartResultDTO){
        BigDecimal priceAfterCoupon = new BigDecimal(0);
        if (coupon.getDiscountType().equals(DiscountType.RATE)) {
            priceAfterCoupon = cartResultDTO.getLastPrice().subtract(cartResultDTO.getLastPrice().multiply(coupon.getDiscount().divide(new BigDecimal(100))));
        } else if (coupon.getDiscountType().equals(DiscountType.AMOUNT)) {
            priceAfterCoupon = cartResultDTO.getLastPrice().subtract(coupon.getDiscount());
        }
        cartResultDTO.setDiscounts(setCouponDiscountInformation(coupon,cartResultDTO,priceAfterCoupon));
        cartResultDTO.setLastPrice(priceAfterCoupon);
        cartResultDTO.setCouponId(coupon.getCouponId());
        cartResultDTO.setCouponUsed(true);
    }

    private List<Map<String, Object>> setCouponDiscountInformation(Coupon coupon,CartResultDTO cartResultDTO,BigDecimal priceAfterCoupon){
        Map<String, Object> couponDiscountInformation = createCouponDiscountInformation(coupon,cartResultDTO.getLastPrice().subtract(priceAfterCoupon));
        List<Map<String, Object>> couponDiscountInformationList = cartResultDTO.getDiscounts();
        if(couponDiscountInformationList == null){
            couponDiscountInformationList = new ArrayList<>();
        }
        couponDiscountInformationList.add(couponDiscountInformation);
        return couponDiscountInformationList;
    }

    private Map<String, Object> createCouponDiscountInformation(Coupon coupon, BigDecimal priceAfterCoupon) {
        Map<String, Object> couponDiscountInformation = new HashMap<>();
        couponDiscountInformation.put("discount_type", "COUPON " + coupon.getDiscountType());
        couponDiscountInformation.put("discount_amount", priceAfterCoupon);
        couponDiscountInformation.put("couponId", coupon.getCouponId());
        return couponDiscountInformation;
    }

    private BigDecimal checkIfHasDiscount(CartResultDTO cartResultDTO) {
        BigDecimal priceAfterCampaign = cartResultDTO.getTotalPrice();
        for (CampaignCountHolder campaignCountHolder : campaignCountHolderMap.values()) {
            Campaign campaign = campaignCountHolder.getCampaign();
            if (campaign.getMinimumQuantity() <= campaignCountHolder.getTotalQuantity()) {
                if (campaign.getDiscountType().equals(DiscountType.RATE)) {
                    priceAfterCampaign = cartResultDTO.getTotalPrice().subtract(cartResultDTO.getTotalPrice().multiply(campaign.getDiscount().divide(new BigDecimal(100))));
                } else if (campaign.getDiscountType().equals(DiscountType.AMOUNT)) {
                    priceAfterCampaign = cartResultDTO.getTotalPrice().subtract(campaign.getDiscount());
                }
                this.setCampaignDiscountInformation(campaign,cartResultDTO,priceAfterCampaign);
            }
        }
        campaignCountHolderMap = new HashMap<>();
        return priceAfterCampaign;
    }

    private void setCampaignDiscountInformation(Campaign campaign,CartResultDTO cartResultDTO,BigDecimal priceAfterCampaign){
        List<Map<String, Object>> campaingDiscountInformationList = cartResultDTO.getDiscounts();
        if(campaingDiscountInformationList == null){
            campaingDiscountInformationList = new ArrayList<>();
        }
        campaingDiscountInformationList.add(createCampaignDiscountInformation(campaign,cartResultDTO.getTotalPrice().subtract(priceAfterCampaign)));
        cartResultDTO.setDiscounts(campaingDiscountInformationList);
    }

    private Map<String, Object> createCampaignDiscountInformation(Campaign campaign, BigDecimal priceAfterCampaign) {
        Map<String, Object> couponDiscountInformation = new HashMap<>();
        couponDiscountInformation.put("discount_type", "CAMPAIGN " + campaign.getDiscountType());
        couponDiscountInformation.put("discount_amount", priceAfterCampaign);
        couponDiscountInformation.put("campaignId", campaign.getCampaignId());
        return couponDiscountInformation;
    }

    private void checkIfHasCampaign(CartItem cartItem) {
        Campaign campaign = campaignRepository.findByCategoryId(cartItem.getCategoryId());
        if (campaign != null) {
            CampaignCountHolder countHolder = campaignCountHolderMap.get(campaign.getCampaignId());
            if (countHolder != null) {
                Integer quantity = countHolder.getTotalQuantity() + cartItem.getQuantity();
                campaignCountHolderMap.put(campaign.getCampaignId(), new CampaignCountHolder(campaign, quantity));
            } else {
                campaignCountHolderMap.put(campaign.getCampaignId(), new CampaignCountHolder(campaign, cartItem.getQuantity()));
            }
        }
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
}
