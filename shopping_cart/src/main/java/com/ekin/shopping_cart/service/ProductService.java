package com.ekin.shopping_cart.service;

import com.ekin.shopping_cart.dto.ProductDTO;
import com.ekin.shopping_cart.entity.Product;

public interface ProductService {
    ProductDTO create(ProductDTO productDTO) throws Exception;
    void delete(Long id);

}
