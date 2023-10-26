package com.example.productservice.respositories;

import com.example.productservice.entities.Product;
import com.example.productservice.respositories.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
    }

    public List<Product> list() {
        return this.products;
    }

    public void add(Product product) {
        this.products.add(product);
    }

    public boolean isQuantityAvailable(Long productId, int quantity) {

        Product product = this.products.stream().filter(p -> p.getProductId().equals(productId))
                .findFirst().orElseThrow(() -> new NotFoundException("Product not found."));

        return (product.getAvailableQuantity() >= quantity);
    }
}
