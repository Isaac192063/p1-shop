package com.primer_parcial.shop.serviceImp;

import com.primer_parcial.shop.exceptions.AlreadyExistsException;
import com.primer_parcial.shop.exceptions.NotFoundException;
import com.primer_parcial.shop.model.Article;
import com.primer_parcial.shop.model.enums.ErrorMessage;
import com.primer_parcial.shop.repository.ArticleRepository;
import com.primer_parcial.shop.repository.CategoryRepository;
import com.primer_parcial.shop.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Article createArticle(Article article) {

        if(!categoryRepository.existsById(article.getCategory().getId())){
            throw  new NotFoundException(ErrorMessage.CATEGORY_NOT_FOUND.getMessage());
        }

        Optional<Article> articleFindByName = articleRepository.findByName(article.getName());
        if(articleFindByName.isPresent()){
            throw new AlreadyExistsException(ErrorMessage.ARTICLE_NAME_EXISTS.getMessage());
        }
        article.setCategory(article.getCategory());
        return articleRepository.save(article);
    }

    @Override
    public Article getArticleById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isEmpty()){
            throw new NotFoundException(ErrorMessage.ARTICLE_NOT_FOUND.getMessage());
        }
        return article.get();
    }

    @Override
    public List<Article> getAllArticle() {
        return (List<Article>)articleRepository.findAll();
    }

    @Override
    public Article updateArticle(Long id, Article updateArticle) {

        Optional<Article> candidateArticle = articleRepository.findById(id);

        if(candidateArticle.isEmpty()){
            throw new NotFoundException(ErrorMessage.ARTICLE_NOT_FOUND.getMessage());
        }

        Optional<Article> articleFindByName =
                articleRepository.findByNameAndIdNot(updateArticle.getName(), id);

        if(articleFindByName.isPresent()){
            throw new AlreadyExistsException(ErrorMessage.ARTICLE_NAME_EXISTS.getMessage());
        }

        Article existingArticle = candidateArticle.get();

        existingArticle.setDescription(updateArticle.getDescription());
        existingArticle.setPrice(updateArticle.getPrice());
        existingArticle.setStock(updateArticle.getStock());
        existingArticle.setName(updateArticle.getName());
        existingArticle.setBrand(updateArticle.getBrand());
        return articleRepository.save(existingArticle);
    }
    @Override
    public Article deleteArticle(Long id){
        Optional<Article> candidateArticle = articleRepository.findById(id);

        if(candidateArticle.isEmpty()){
            throw new NotFoundException(ErrorMessage.ARTICLE_NOT_FOUND.getMessage());
        }

        Article article =candidateArticle.get();
        article.setAvailable(false);

        return  articleRepository.save(article);
    }
}
