package com.primer_parcial.shop.model.enums;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    ARTICLE_NOT_FOUND("Article not found!"),
    ARTICLE_NAME_EXISTS("The article is already registered!"),
    CATEGORY_NOT_FOUND("Category not found!"),
    CATEGORY_NAME_EXISTS("The category is already registered!");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;}
}
