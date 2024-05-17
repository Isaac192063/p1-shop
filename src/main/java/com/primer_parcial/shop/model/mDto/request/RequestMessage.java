package com.primer_parcial.shop.model.mDto.request;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RequestMessage<T> {
    private String messageID;
    private T obj;
}
