package com.primer_parcial.shop.controller;

import com.primer_parcial.shop.model.Article;
import com.primer_parcial.shop.service.article.ArticleServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    private ArticleServiceImp articleService;


    @PostMapping()
    public ResponseEntity<Article> createArticle(@RequestBody @Valid Article article){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(articleService.createArticle(article));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(articleService.getArticleById(id));
    }

    @GetMapping()
    public ResponseEntity<List<Article>> getArticleAll(){
        return ResponseEntity.ok().body(articleService.getAllArticle());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(
            @PathVariable @Positive Long id,@RequestBody @Valid Article article){
        return ResponseEntity.ok().body(articleService.updateArticle(id, article));
    }
}
