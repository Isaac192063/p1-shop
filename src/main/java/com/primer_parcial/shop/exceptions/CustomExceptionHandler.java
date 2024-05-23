package com.primer_parcial.shop.exceptions;

import com.primer_parcial.shop.model.dto.Response;
import com.primer_parcial.shop.model.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ResponseError> AlreadyExistsException(
            AlreadyExistsException alreadyExistsException){
        return new ResponseEntity<>(
                ResponseError.builder()
                        .date(LocalDateTime.now())
                        .message(List.of(alreadyExistsException.getMessage()))
                        .statusCode((HttpStatus.CONFLICT.value()))
                        .build(), HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> notFoundExceptionHandler(
            NotFoundException notFoundException) {
        return new ResponseEntity<>(
                ResponseError.builder()
                        .date(LocalDateTime.now())
                        .message(List.of(notFoundException.getMessage()))
                        .statusCode((HttpStatus.NOT_FOUND.value()))
                        .build(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleValidationException(MethodArgumentNotValidException ex){
        List<String> errors = new ArrayList<>();
        System.out.println("hola mundo");
        ex.getBindingResult().getAllErrors().forEach((error ) -> {
            String[] fieldParts = ((FieldError) error).getField().split("\\.");
            String fieldName = fieldParts[fieldParts.length - 1];
            errors.add(fieldName.concat(" - " + error.getDefaultMessage()));
        });
        return new ResponseEntity<>(
                ResponseError.builder()
                        .date(LocalDateTime.now())
                        .message(errors)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handleConstraintViolationException(
            MethodArgumentTypeMismatchException ex){
        return new ResponseEntity<>(
                ResponseError.builder()
                        .date(LocalDateTime.now())
                        .message(List.of("The value must be an integer"))
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}
