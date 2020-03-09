package com.ekin.shopping_cart.entity;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY_TABLE")
public class Category {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @Column(name = "CATEGORY_PARENT_ID")
    private Long parentId;

    @Column(name = "CATEGORY_TITLE")
    private String title;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
