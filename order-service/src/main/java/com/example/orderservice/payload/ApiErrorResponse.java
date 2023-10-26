package com.example.orderservice.payload;

import org.springframework.http.HttpStatus;

public record ApiErrorResponse(String message, HttpStatus status) {
}
