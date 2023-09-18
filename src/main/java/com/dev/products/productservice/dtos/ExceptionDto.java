package com.dev.products.productservice.dtos;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionDto {

    private String message;
    private HttpStatus httpStatus;

    public ExceptionDto(HttpStatus httpStatus, String message) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
