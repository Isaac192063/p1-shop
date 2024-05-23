package com.primer_parcial.shop.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ResponseError {
    private LocalDateTime date;
    private int statusCode;
    private List<String> message;
}
