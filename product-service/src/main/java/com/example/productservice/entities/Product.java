package com.example.productservice.entities;

public class Product {
    private Long productId;
    private String name;
    private int availableQuantity;

    public Product(Long productId, String name, int availableQuantity) {
        this.productId = productId;
        this.name = name;
        this.availableQuantity = availableQuantity;
    }

    public Long getProductId() {
        return this.productId;
    }

    public String getName() {
        return this.name;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}
