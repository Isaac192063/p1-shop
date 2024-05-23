package com.primer_parcial.shop.model.dto;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class Request<T> {
    @Valid
    private RequestMessage<T> requestMessage;

    @Data
    public static class RequestMessage<Type>{
        private String messageID;
        @Valid
        private Type data;
    }
}
