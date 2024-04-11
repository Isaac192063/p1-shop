package com.primer_parcial.shop.controller;

import com.primer_parcial.shop.model.Category;
import com.primer_parcial.shop.service.category.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryService;

    @PostMapping()
    public Category createCategory(@RequestBody Category category){
        System.out.println(category.getName());
        return categoryService.createCategory(category);
    }

    @GetMapping()
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @PutMapping("/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable Long id){
        return categoryService.updateCategory(id, category);
    }

    @GetMapping("/{id}")
    public Category getCategoryByid(@PathVariable Long  id){
        return categoryService.getCategoryById(id);
    }

}
