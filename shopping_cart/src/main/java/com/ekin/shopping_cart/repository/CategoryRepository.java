package com.ekin.shopping_cart.repository;

import com.ekin.shopping_cart.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
