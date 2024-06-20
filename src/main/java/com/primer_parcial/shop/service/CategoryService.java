package com.primer_parcial.shop.service;

import java.util.List;

import com.primer_parcial.shop.model.Category;

public interface CategoryService {
    Category createCategory(Category article);
    Category getCategoryById(Long id);
    List<Category> getAllCategory();
    Category updateCategory(Long id, Category newArticle);
}
