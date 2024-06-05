package com.primer_parcial.shop.controller;

import com.primer_parcial.shop.model.Category;
import com.primer_parcial.shop.model.dto.Request;
import com.primer_parcial.shop.model.dto.Response;
import com.primer_parcial.shop.serviceImp.CategoryServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryService;

    @PostMapping()
    public ResponseEntity<Response<Category>> createCategory(@RequestBody @Valid Request<Category> categoryRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<Category>builder()
                                .date(LocalDateTime.now())
                                .message(categoryService.createCategory(categoryRequest.getRequestMessage().getData()))
                                .statusCode(HttpStatus.CREATED.value())
                                .build());
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok().body(categoryService.getAllCategory());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Category>> updateCategory(
            @RequestBody @Valid Request<Category> categoryRequest, @PathVariable @Positive Long id){
        return ResponseEntity.ok().body(
                Response.<Category>builder()
                        .date(LocalDateTime.now())
                        .message(categoryService.updateCategory(id, categoryRequest.getRequestMessage().getData()))
                        .statusCode(HttpStatus.OK.value())
                        .build()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Category>> getCategoryById(@PathVariable @Positive Long  id){
        return ResponseEntity.ok().body(
                Response.<Category>builder()
                        .date(LocalDateTime.now())
                        .message(categoryService.getCategoryById(id))
                        .statusCode(HttpStatus.OK.value())
                        .build()
                );
    }


}
