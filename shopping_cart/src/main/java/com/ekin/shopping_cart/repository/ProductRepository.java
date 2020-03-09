package com.ekin.shopping_cart.repository;

import com.ekin.shopping_cart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
