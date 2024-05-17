package com.primer_parcial.shop.model.mDto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Request<T> {
    private RequestMessage<T> requestMessage;
}
