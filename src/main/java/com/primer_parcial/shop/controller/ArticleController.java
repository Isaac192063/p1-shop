package com.primer_parcial.shop.controller;

import com.primer_parcial.shop.model.Article;
import com.primer_parcial.shop.service.article.ArticleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    private ArticleServiceImp articleService;


    @PostMapping()
    public Article createArticle(@RequestBody Article article){
        return articleService.createArticle(article);
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id){
        return articleService.getArticleById(id);
    }

    @GetMapping()
    public List<Article> getArticleAll(){
        return articleService.getAllArticle();
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id,@RequestBody Article article){
        return articleService.updateArticle(id, article);
    }

}
