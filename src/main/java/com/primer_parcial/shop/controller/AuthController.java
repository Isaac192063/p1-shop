package com.primer_parcial.shop.controller;

import com.primer_parcial.shop.model.User;
import com.primer_parcial.shop.model.dto.AuthRequest;
import com.primer_parcial.shop.model.dto.AuthResponse;
import com.primer_parcial.shop.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private AuthService authService;
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest){
        return  ResponseEntity.ok(AuthResponse.builder().build());
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody User user){
        return ResponseEntity.ok(AuthResponse.builder().build());
    }
}
