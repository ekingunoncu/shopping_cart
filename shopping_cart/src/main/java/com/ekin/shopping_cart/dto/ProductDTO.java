package com.ekin.shopping_cart.dto;

import com.ekin.shopping_cart.entity.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductDTO {

    private Long productId;
    @NotEmpty(message = "title field can not be null.")
    private String title;
    @NotNull(message = "price field can not be null.")
    private BigDecimal price;
    @NotNull(message = "categoryId field can not be null.")
    private Long categoryId;
    private Integer quantity;

    public Product toEntity(){
        Product product = new Product();
        product.setCategoryId(categoryId);
        product.setPrice(price);
        product.setTitle(title);
        product.setProductId(this.productId);
        return product;
    }

    public static ProductDTO toDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategoryId(product.getCategoryId());
        productDTO.setPrice(product.getPrice());
        productDTO.setProductId(product.getProductId());
        productDTO.setTitle(product.getTitle());
        return productDTO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
