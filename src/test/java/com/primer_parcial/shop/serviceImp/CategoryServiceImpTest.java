package com.primer_parcial.shop.serviceImp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.primer_parcial.shop.exceptions.AlreadyExistsException;
import com.primer_parcial.shop.exceptions.NotFoundException;
import com.primer_parcial.shop.model.Category;
import com.primer_parcial.shop.model.enums.ErrorMessages;
import com.primer_parcial.shop.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImpTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImp categoryServiceImp;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category();
        category.setId(1L);
        category.setName("Moda");
        category.setDescription("Ropa de toda estilo");
    }

    @DisplayName("creacion de categorias")
    @Test
    void createCategory() {
        given(categoryRepository.findByName(category.getName()))
                .willReturn(Optional.empty());
        given(categoryRepository.save(category)).willReturn(category);

        Category categorySave = categoryServiceImp.createCategory(category);

        assertThat(categorySave).isNotNull();
        assertThat(categorySave.getName()).isEqualTo(category.getName());
    }

    @DisplayName("Crear categoría - Ya existe")
    @Test
    void createCategory_AlreadyExists() {
        given(categoryRepository.findByName(category.getName())).willReturn(Optional.of(category));

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> {
            categoryServiceImp.createCategory(category);
        });

        assertThat(exception.getMessage()).isEqualTo(ErrorMessages.CATEGORY_NAME_EXISTS.getMessage());
    }


    @DisplayName("Obtener categoría por ID - Éxito")
    @Test
    void getCategoryById() {
        given(categoryRepository.findById(1L)).willReturn(Optional.of(category));

        Category categoryCandidate = categoryServiceImp.getCategoryById(category.getId());

        assertThat(categoryCandidate).isNotNull();
        assertThat(categoryCandidate.getName()).isEqualTo(category.getName());
    }

    @DisplayName("Obtener categoría por ID - No encontrada")
    @Test
    void getCategoryById_NotFound() {
        given(categoryRepository.findById(1L)).willReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            categoryServiceImp.getCategoryById(1L);
        });

        assertThat(exception.getMessage()).isEqualTo(ErrorMessages.CATEGORY_NOT_FOUND.getMessage());
    }

    @DisplayName("traer todas las category")
    @Test
    void getAllCategory(){
        Category category1 = new Category();
        category1.setDescription("categoria dedicada al gaming");
        category1.setName("juegos");
        category1.setId(2L);
        given(categoryRepository.findAll()).willReturn(List.of(category, category1));

        List<Category> categories = categoryServiceImp.getAllCategory();

        assertThat(categories).isNotNull();
        assertThat(categories.size()).isEqualTo(2);
    }
    @DisplayName("Actualización de categorias")
    @Test
    void updateCategory() {

        category.setId(1L);
        category.setName("hogar");
        category.setDescription("Productos para el hogar y aseo");

        given(categoryRepository.findById(1L)).willReturn(Optional.of(category));
        given(categoryRepository.findByNameAndIdNot(category.getName(), 1L)).willReturn(Optional.empty());
        given(categoryRepository.save(category)).willReturn(category);


        Category categoryUpdate = categoryServiceImp.updateCategory(category.getId(), category);

        assertThat(categoryUpdate.getName()).isEqualTo("hogar");
        assertThat(categoryUpdate.getDescription()).isEqualTo("Productos para el hogar y aseo");
    }

    @DisplayName("Actualizar categoría - No encontrada")
    @Test
    void updateCategory_NotFound() {
        Category newCategory = new Category();
        newCategory.setName("hogar");
        newCategory.setDescription("Productos para el hogar y aseo");

        given(categoryRepository.findById(1L)).willReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            categoryServiceImp.updateCategory(1L, newCategory);
        });

        assertThat(exception.getMessage()).isEqualTo(ErrorMessages.CATEGORY_NOT_FOUND.getMessage());
    }

    @DisplayName("Actualizar categoría - Nombre ya existe")
    @Test
    void updateCategory_NameAlreadyExists() {
        Category newCategory = new Category();
        newCategory.setName("hogar");
        newCategory.setDescription("Productos para el hogar y aseo");

        given(categoryRepository.findById(1L)).willReturn(Optional.of(category));
        given(categoryRepository.findByNameAndIdNot(newCategory.getName(), 1L)).willReturn(Optional.of(new Category()));

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> {
            categoryServiceImp.updateCategory(1L, newCategory);
        });

        assertThat(exception.getMessage()).isEqualTo(ErrorMessages.CATEGORY_NAME_EXISTS.getMessage());
    }

}