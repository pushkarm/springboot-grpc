package com.example.productservice.services;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.entities.Product;
import com.example.productservice.respositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> list() {
        List<Product> list = productRepository.list();
        return list.stream().map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    private ProductDto mapToProductDto(Product product) {
        return new ProductDto(product.getProductId(), product.getName(), product.getAvailableQuantity());
    }
}
