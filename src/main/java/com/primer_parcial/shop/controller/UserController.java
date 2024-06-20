package com.primer_parcial.shop.controller;

import com.primer_parcial.shop.model.User;
import com.primer_parcial.shop.model.dto.Request;
import com.primer_parcial.shop.model.dto.Response;
import com.primer_parcial.shop.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @GetMapping("/{id}")
    public ResponseEntity<Response<User>> getUserByIda(@PathVariable @Positive Long id) {
        return ResponseEntity
                .ok()
                .body(Response.<User>builder()
                        .date(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .message(userService.getUserById(id))
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<User>> updateUser(@RequestBody @Valid Request<User> user, @PathVariable  @Positive Long id) {
        System.out.println("hello");
        return ResponseEntity
                .ok()
                .body(Response.<User>builder()
                        .date(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .message(userService.updateUser(id, user.getRequestMessage().getData()))
                        .build());
    }

    @GetMapping()
    public ResponseEntity<Response<List<User>>> allUsers() {
        return ResponseEntity
                .ok()
                .body(Response.<List<User>>builder()
                        .date(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .message(userService.getAllUser())
                        .build());
    }
}

