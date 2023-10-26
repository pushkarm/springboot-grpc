package com.example.orderservice.payload;

public record OrderRequest(long productId, int quantity) {
}
