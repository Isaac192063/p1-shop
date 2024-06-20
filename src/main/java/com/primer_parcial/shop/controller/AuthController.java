package com.primer_parcial.shop.controller;

import com.primer_parcial.shop.exceptions.AuthenticationFailedException;
import com.primer_parcial.shop.model.User;
import com.primer_parcial.shop.model.dto.AuthRequest;
import com.primer_parcial.shop.model.dto.AuthResponse;
import com.primer_parcial.shop.model.dto.Request;
import com.primer_parcial.shop.model.dto.Response;
import com.primer_parcial.shop.model.enums.ErrorMessage;
import com.primer_parcial.shop.serviceImp.AuthService;
import com.primer_parcial.shop.serviceImp.UserServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserServiceImp serviceImp;
    @PostMapping("register")
    public ResponseEntity<Response<User>> register( @RequestBody @Valid  Request<User> user){

        return ResponseEntity
                .status(HttpStatus.CREATED).body(
                    Response.<User>builder()
                    .date(LocalDateTime.now())
                    .message(serviceImp.createUser(user.getRequestMessage().getData()))
                    .statusCode(HttpStatus.CREATED.value())
                    .build()
                );
    }
    @PostMapping("login")
    public ResponseEntity<Response<AuthResponse>> login(@Valid @RequestBody Request<AuthRequest> authRequest){
        try {
            AuthResponse authResponse = authService.login(authRequest.getRequestMessage().getData());
            return ResponseEntity.ok(Response.<AuthResponse>builder()
                            .statusCode(HttpStatus.OK.value())
                            .message(authResponse)
                            .date(LocalDateTime.now())
                    .build());
        } catch (AuthenticationFailedException e) {
            throw  new AuthenticationFailedException(ErrorMessage.CREDENTIAL_INVALID.getMessage());
        }
    }

}
