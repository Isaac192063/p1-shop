package com.primer_parcial.shop.service.category;

import com.primer_parcial.shop.exceptions.AlreadyExistsException;
import com.primer_parcial.shop.exceptions.NotFoundException;
import com.primer_parcial.shop.model.Article;
import com.primer_parcial.shop.model.Category;
import com.primer_parcial.shop.model.enums.ErrorMessages;
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
        Optional<Category> categoryFindByName = categoryRepository.findByName(category.getName());
        if(categoryFindByName.isPresent()){
            throw new AlreadyExistsException(ErrorMessages.CATEGORY_NAME_EXISTS.getMessage());
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new NotFoundException("Category not found!");
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
            throw new NotFoundException("Category not found!");
        }

        Optional<Category> categoryFindByName =
                categoryRepository.findByNameAndIdNot(newArticle.getName(), id);

        if(categoryFindByName.isPresent()){
            throw new AlreadyExistsException(ErrorMessages.CATEGORY_NAME_EXISTS.getMessage());
        }

        Category category = candidateCategory.get();

        category.setName(newArticle.getName());
        return  categoryRepository.save(category);
    }
}
