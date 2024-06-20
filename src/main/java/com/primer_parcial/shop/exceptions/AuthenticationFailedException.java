package com.primer_parcial.shop.exceptions;

public class AuthenticationFailedException extends RuntimeException{
    public  AuthenticationFailedException(String message){
        super(message);
    }
}
