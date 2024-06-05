package com.primer_parcial.shop.controller;

import com.primer_parcial.shop.model.Article;
import com.primer_parcial.shop.model.dto.Request;
import com.primer_parcial.shop.model.dto.Response;
import com.primer_parcial.shop.serviceImp.ArticleServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    private ArticleServiceImp articleService;


    @PostMapping()
    public ResponseEntity<Response<Article>> createArticle(@RequestBody @Valid Request<Article> articleRequest){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<Article>builder()
                        .date(LocalDateTime.now())
                        .message(articleService.createArticle(articleRequest.getRequestMessage().getData()))
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Article>> getArticleById(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(Response.<Article>builder()
                .date(LocalDateTime.now())
                .message(articleService.getArticleById(id))
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @GetMapping()
    public ResponseEntity<List<Article>> getArticleAll(){
        return ResponseEntity.ok().body(articleService.getAllArticle());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Article>> updateArticle(
            @PathVariable @Positive Long id,@RequestBody @Valid Request<Article> articleRequest){
        return ResponseEntity.ok().body(
                Response.<Article>builder()
                        .statusCode(HttpStatus.OK.value())
                        .date(LocalDateTime.now())
                        .message(articleService.updateArticle(id, articleRequest.getRequestMessage().getData()))
                        .build()
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Article>> deleteArticle(@PathVariable Long id){

        return ResponseEntity.ok().body(Response
                .<Article>builder()
                        .statusCode(HttpStatus.OK.value())
                        .date(LocalDateTime.now())
                        .message(articleService.deleteArticle(id))
                .build());
    }

}
