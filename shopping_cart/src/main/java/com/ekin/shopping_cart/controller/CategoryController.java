package com.ekin.shopping_cart.controller;

import com.ekin.shopping_cart.dto.CategoryDTO;
import com.ekin.shopping_cart.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(categoryDTO));
    }

    @DeleteMapping
    public @ResponseBody ResponseEntity delete(@RequestParam Long id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
