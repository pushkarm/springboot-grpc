package com.example.orderservice.controllers;

import com.example.orderservice.exceptions.ProductNotAvailableException;
import com.example.orderservice.payload.ApiErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ProductNotAvailableException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ApiErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
