package com.primer_parcial.shop.service;

import com.primer_parcial.shop.model.User;
import com.primer_parcial.shop.model.dto.AuthResponse;
import com.primer_parcial.shop.model.dto.RegisterRequest;
import com.primer_parcial.shop.model.enums.Role;
import com.primer_parcial.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    public AuthResponse login(){
        return  null;
    }

    public AuthResponse register(RegisterRequest registerRequest){
        User user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .fullBirthDay(registerRequest.getFullBirthDay())
                .password(registerRequest.getPassword())
                .phoneNumber(registerRequest.getPhoneNumber())
                .document(registerRequest.getDocument())
                .typeDocument(registerRequest.getTypeDocument())
                .address(List.of(registerRequest.getAddress()))
                .role(Role.USER)
                .build();

        return AuthResponse.builder()
                .token(null)
                .build();
    }
}
