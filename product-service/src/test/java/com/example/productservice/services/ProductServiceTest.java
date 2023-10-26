package com.example.productservice.services;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.entities.Product;
import com.example.productservice.respositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Test
    public void shouldListProducts() {

        Product product = new Product(1L, "name", 1);
        Mockito.when(productRepository.list()).thenReturn(List.of(product));

        ProductService productService = new ProductService(productRepository);
        List<ProductDto> list = productService.list();

        Assertions.assertNotNull(list, "Product list should not be null");

        Mockito.verify(productRepository, Mockito.times(1)).list();
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(1L, list.get(0).productId());

    }
}