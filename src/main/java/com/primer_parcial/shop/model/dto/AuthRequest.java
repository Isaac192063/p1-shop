package com.primer_parcial.shop.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
public class AuthRequest {
    @NotBlank(message = "Email is required")
    @Email(message ="Email is not valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8)
    private String password;
}
