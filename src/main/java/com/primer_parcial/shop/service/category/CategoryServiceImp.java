package com.primer_parcial.shop.service.category;

import com.primer_parcial.shop.model.Category;
import com.primer_parcial.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            return  null;
        }
        return category.get();
    }

    @Override
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id,Category newArticle) {
        Optional<Category> candidateCategory = categoryRepository.findById(id);

        if(candidateCategory.isEmpty()){
            return  null;
        }

        Category category = candidateCategory.get();

        category.setName(newArticle.getName());
        category.setDescription(newArticle.getDescription());
        return  categoryRepository.save(category);
    }
}
