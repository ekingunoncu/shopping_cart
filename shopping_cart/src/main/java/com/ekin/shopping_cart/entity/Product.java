package com.ekin.shopping_cart.entity;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT_TABLE")
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(name = "PRODUCT_TITLE")
    private String title;

    @Column(name = "PRODUCT_PRICE")
    private BigDecimal price;

    @Column(name = "CATEGORY_ID")
    private Long categoryId;

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
