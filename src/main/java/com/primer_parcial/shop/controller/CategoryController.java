package com.primer_parcial.shop.controller;

import com.primer_parcial.shop.model.Category;
import com.primer_parcial.shop.service.category.CategoryServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryService;

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category){
        System.out.println(category.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.createCategory(category));
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok().body(categoryService.getAllCategory());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @RequestBody @Valid Category category, @PathVariable @Positive Long id){
        return ResponseEntity.ok().body(categoryService.updateCategory(id, category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable @Positive Long  id){
        return ResponseEntity.ok().body(categoryService.getCategoryById(id));
    }

}
