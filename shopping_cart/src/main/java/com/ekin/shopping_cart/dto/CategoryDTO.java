package com.ekin.shopping_cart.dto;

import com.ekin.shopping_cart.entity.Category;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private Long categoryId;
    private Long parentId;
    @NotNull(message = "title field can not be null.")
    private String title;

    public Category toEntity() {
        Category category = new Category();
        category.setCategoryId(this.getCategoryId());
        category.setParentId(this.getParentId());
        category.setTitle(this.getTitle());
        return category;
    }

    public static CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setParentId(category.getParentId());
        categoryDTO.setTitle(category.getTitle());
        return categoryDTO;
    }

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
