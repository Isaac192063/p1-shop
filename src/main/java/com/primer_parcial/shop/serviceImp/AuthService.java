package com.primer_parcial.shop.serviceImp;

import com.primer_parcial.shop.exceptions.AlreadyExistsException;
import com.primer_parcial.shop.exceptions.AuthenticationFailedException;
import com.primer_parcial.shop.model.User;
import com.primer_parcial.shop.model.dto.AuthRequest;
import com.primer_parcial.shop.model.dto.AuthResponse;
import com.primer_parcial.shop.model.enums.ErrorMessage;
import com.primer_parcial.shop.model.enums.Role;
import com.primer_parcial.shop.repository.UserRepository;
import com.primer_parcial.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final JWTservice jwTservice;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(AuthRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(), authRequest.getPassword()
            ));
        }catch (Exception e){
            throw new AuthenticationFailedException(ErrorMessage.CREDENTIAL_INVALID.getMessage());
        }
        Optional<User> user = userRepository.findByEmail(authRequest.getEmail());

        if (user.isEmpty()){
            throw new AuthenticationFailedException(ErrorMessage.CREDENTIAL_INVALID.getMessage());
        }
        UserDetails userDetails = user.get();
        String token = jwTservice.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

}
