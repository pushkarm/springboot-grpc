package com.example.orderservice.entities;

import java.util.UUID;

public class Order {

    private UUID id;

    private Long productId;

    private int quantity;

    public Order(long productId, int quantity) {
        this.id = UUID.randomUUID();
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return this.productId;
    }

    public UUID getId() {
        return this.id;
    }
}
