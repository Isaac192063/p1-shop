package com.primer_parcial.shop.model.mDto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ResponseMessage {
    private LocalDate date;
    private String message;
    private String statusCode;
}
