package com.example.productservice;

import com.example.productservice.entities.Product;
import com.example.productservice.respositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@PostConstruct
	public void init() {
		productRepository.add(new Product(1L, "name", 10));
	}

}
