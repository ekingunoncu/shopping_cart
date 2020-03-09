package com.ekin.shopping_cart.service;

import com.ekin.shopping_cart.dto.ProductDTO;
import com.ekin.shopping_cart.repository.CategoryRepository;
import com.ekin.shopping_cart.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) throws Exception {
        if(categoryRepository.findById(productDTO.getCategoryId()).isPresent()){
            return ProductDTO.toDTO(productRepository.save(productDTO.toEntity()));
        }else{
            throw new Exception("No category with given id.");
        }
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
