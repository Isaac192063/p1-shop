package com.primer_parcial.shop.service.article;

import com.primer_parcial.shop.exceptions.AlreadyExistsException;
import com.primer_parcial.shop.exceptions.NotFoundException;
import com.primer_parcial.shop.model.Article;
import com.primer_parcial.shop.model.enums.ErrorMessages;
import com.primer_parcial.shop.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article createArticle(Article article) {
        Optional<Article> articleFindByName = articleRepository.findByName(article.getName());
        if(articleFindByName.isPresent()){
            throw new AlreadyExistsException(ErrorMessages.ARTICLE_NAME_EXISTS.getMessage());
        }
        article.setCategory(article.getCategory());
        return articleRepository.save(article);
    }

    @Override
    public Article getArticleById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isEmpty()){
            throw new NotFoundException("Article not found!");
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
            throw new NotFoundException("Article not found!");
        }

        Optional<Article> articleFindByName =
                articleRepository.findByNameAndIdNot(updateArticle.getName(), id);

        if(articleFindByName.isPresent()){
            throw new AlreadyExistsException(ErrorMessages.ARTICLE_NAME_EXISTS.getMessage());
        }

        Article existingArticle = candidateArticle.get();

        existingArticle.setDescription(updateArticle.getDescription());
        existingArticle.setPrice(updateArticle.getPrice());
        existingArticle.setUptated_at(LocalDate.now());

        return articleRepository.save(existingArticle);
    }
}
