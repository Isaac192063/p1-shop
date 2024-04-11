package com.primer_parcial.shop.service.article;

import com.primer_parcial.shop.model.Article;
import com.primer_parcial.shop.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article getArticleById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        System.out.println(id);
        if (article.isEmpty()){
            return null;
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
            return null;
        }

        Article existingArticle = candidateArticle.get();

        existingArticle.setDescription(updateArticle.getDescription());
        existingArticle.setPrice(updateArticle.getPrice());
        existingArticle.setStock(updateArticle.getStock());
        existingArticle.setName(updateArticle.getName());
        existingArticle.setBrand(updateArticle.getBrand());
        return articleRepository.save(existingArticle);
    }
}
