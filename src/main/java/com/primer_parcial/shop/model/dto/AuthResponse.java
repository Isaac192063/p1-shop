package com.primer_parcial.shop.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class AuthResponse {
    private String token;
}
