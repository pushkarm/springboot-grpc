package com.example.orderservice.controllers;

import com.example.orderservice.payload.OrderRequest;
import com.example.orderservice.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @MockBean
    OrderService orderService;

    @Test
    public void shouldCreateOrder() throws Exception {

        OrderRequest orderRequest = new OrderRequest(12L, 1);
        String jsonRequestString = this.objectMapper.writeValueAsString(orderRequest);

        this.mockMvc.perform(
                    MockMvcRequestBuilders.post("/orders")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(jsonRequestString))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(orderService, Mockito.times(1)).createOrder(orderRequest);
    }
}