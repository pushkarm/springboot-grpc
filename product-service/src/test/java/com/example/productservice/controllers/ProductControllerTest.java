package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.services.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Test
    public void shouldListAllProducts() throws Exception {

        ProductDto productDto = new ProductDto(1L, "name", 1);
        Mockito.when(productService.list()).thenReturn(List.of(productDto));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.equalToIgnoringCase("name")))
                .andExpect(jsonPath("$[0].productId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].availableQuantity", Matchers.is(1)));
        Mockito.verify(productService, Mockito.times(1)).list();

    }

}