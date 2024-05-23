package com.primer_parcial.shop.service.article;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.primer_parcial.shop.exceptions.AlreadyExistsException;
import com.primer_parcial.shop.exceptions.NotFoundException;
import com.primer_parcial.shop.model.Article;
import com.primer_parcial.shop.model.enums.ErrorMessages;
import com.primer_parcial.shop.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


class ArticleServiceImpTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImp articleServiceImp;

    private Article article;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        article = new Article();
        article.setId(1L);
        article.setStock(12);
        article.setName("camara");
        article.setDescription("Una camara que toma fotos");
        article.setBrand("Samsung");
        article.setCreated_at(LocalDateTime.now());
    }
    @DisplayName("crear articulo ")
    @Test
    void createArticle() {
        given(articleRepository.findByName(article.getName()))
                .willReturn(Optional.empty());
        given(articleRepository.save(article)).willReturn(article);

        Article articleSave = articleServiceImp.createArticle(article);

        assertThat(articleSave).isNotNull();
    }

    @DisplayName("Crear articulo - Ya existe")
    @Test
    void createarticle_AlreadyExists() {
        given(articleRepository.findByName(article.getName())).willReturn(Optional.of(article));

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> {
            articleServiceImp.createArticle(article);
        });

        assertThat(exception.getMessage()).isEqualTo(ErrorMessages.ARTICLE_NAME_EXISTS.getMessage());
    }
    @DisplayName("Obtener articulo por ID")
    @Test
    void getArticleById() {
        given(articleRepository.findById(article.getId())).willReturn(Optional.of(article));

        Article articleCandidate = articleServiceImp.getArticleById(article.getId());

        assertThat(articleCandidate).isNotNull();
        assertThat(article.getName()).isEqualTo(articleCandidate.getName());
    }

    @DisplayName("Obtener articulo por ID - No encontrada")
    @Test
    void getarticleById_NotFound() {
        given(articleRepository.findById(1L)).willReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            articleServiceImp.getArticleById(1L);
        });

        assertThat(exception.getMessage()).isEqualTo(ErrorMessages.ARTICLE_NOT_FOUND.getMessage());
    }
    @DisplayName("listar articulo")
    @Test
    void getAllArticle() {
        Article article2 = new Article();
        article2.setId(1L);
        article2.setName("samsung celular");
        article2.setDescription("Telefono celular inteligente");
        article2.setBrand("samsung");
        article2.setStock(5);
        article2.setPrice(20000);
        article2.setCreated_at(LocalDateTime.now());

        given(articleRepository.findAll()).willReturn(List.of(article, article2));

        List<Article> articleList = articleServiceImp.getAllArticle();

        assertThat(articleList).isNotNull();
        assertThat(articleList.size()).isEqualTo(2);
    }
    @DisplayName("Actualoizar articulos")
    @Test
    void updateArticle() {
        article.setName("samsung celular");
        article.setDescription("Telefono celular inteligente");
        article.setBrand("samsung");
        article.setStock(5);
        article.setPrice(20000);

        given(articleRepository.findById(1L)).willReturn(Optional.of(article));
        given(articleRepository.findByNameAndIdNot(article.getName(), 1L)).willReturn(Optional.empty());
        given(articleRepository.save(article)).willReturn(article);

        Article artcicleUpdate = articleServiceImp.updateArticle(article.getId(), article);

        assertThat(artcicleUpdate.getName()).isEqualTo("samsung celular");
        assertThat(artcicleUpdate.getDescription()).isEqualTo("Telefono celular inteligente");
        assertThat(artcicleUpdate.getBrand()).isEqualTo("samsung");
        assertThat(artcicleUpdate.getStock()).isEqualTo(5);
        assertThat(artcicleUpdate.getPrice()).isEqualTo(20000);

    }

    @DisplayName("Actualizar categoría - No encontrada")
    @Test
    void updatearticle_NotFound() {
        article.setName("samsung celular");
        article.setDescription("Telefono celular inteligente");
        article.setBrand("samsung");
        article.setStock(5);
        article.setPrice(20000);

        given(articleRepository.findById(1L)).willReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            articleServiceImp.updateArticle(1L, article);
        });

        assertThat(exception.getMessage()).isEqualTo(ErrorMessages.ARTICLE_NOT_FOUND.getMessage());
    }

    @DisplayName("Actualizar categoría - Nombre ya existe")
    @Test
    void updatearticle_NameAlreadyExists() {
        Article newarticle = new Article();
        newarticle.setName("hogar");
        newarticle.setDescription("Productos para el hogar y aseo");

        given(articleRepository.findById(1L)).willReturn(Optional.of(article));
        given(articleRepository.findByNameAndIdNot(newarticle.getName(), 1L)).willReturn(Optional.of(new Article()));

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> {
            articleServiceImp.updateArticle(1L, newarticle);
        });

        assertThat(exception.getMessage()).isEqualTo(ErrorMessages.ARTICLE_NAME_EXISTS.getMessage());
    }
    
}