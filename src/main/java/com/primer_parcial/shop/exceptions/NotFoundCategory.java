package com.primer_parcial.shop.exceptions;

public class NotFoundCategory extends RuntimeException{
    public NotFoundCategory(String messgae){
        super(messgae);
    }
}
