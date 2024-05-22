package com.primer_parcial.shop.model.mDto.request;

import lombok.Data;

@Data
public class Request<T> {
    private RequestMessage<T> requestMessage;

    @Data
    public static class RequestMessage<Type>{
        private String messageID;
        private Type data;
    }
}
