package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products")
    public List<ProductDto> getPtroductList() {
        return this.productService.list();
    }
}
