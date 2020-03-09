package com.ekin.shopping_cart.service;

import com.ekin.shopping_cart.dto.CategoryDTO;

public interface CategoryService {
    CategoryDTO create(CategoryDTO categoryDTO);
    void delete(Long id);
}
