package com.ekin.shopping_cart.service;


import com.ekin.shopping_cart.dto.CategoryDTO;
import com.ekin.shopping_cart.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        return CategoryDTO.toDTO(categoryRepository.save(categoryDTO.toEntity()));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
