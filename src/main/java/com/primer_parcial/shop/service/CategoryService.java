package com.primer_parcial.shop.service;

import com.primer_parcial.shop.model.Article;
import com.primer_parcial.shop.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category article);
    Category getCategoryById(Long id);
    List<Category> getAllCategory();
    Category updateCategory(Long id, Category newArticle);
}
