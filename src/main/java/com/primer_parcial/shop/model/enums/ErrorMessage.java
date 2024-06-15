package com.primer_parcial.shop.model.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    ARTICLE_NOT_FOUND("Article not found!"),
    ARTICLE_NAME_EXISTS("The article is already registered!"),
    CATEGORY_NOT_FOUND("Category not found!"),
    CATEGORY_NAME_EXISTS("The category is already registered!"),
    USER_NOT_FOUND("user not found"),
    USER_EMAIL_EXIST("the email is already registered"),
    CREDENTIAL_INVALID("the credential is invalid");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;}
}
