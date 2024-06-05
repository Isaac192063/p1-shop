package com.primer_parcial.shop.validators;

import com.primer_parcial.shop.model.Category;
import com.primer_parcial.shop.repository.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryExistValidator implements ConstraintValidator<CategoriaExists, Category> {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public boolean isValid(Category value, ConstraintValidatorContext context) {
        return categoryRepository.existsById(value.getId());
    }
}
