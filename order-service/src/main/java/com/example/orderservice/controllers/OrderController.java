package com.example.orderservice.controllers;

import com.example.orderservice.entities.Order;
import com.example.orderservice.payload.OrderRequest;
import com.example.orderservice.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/orders")
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        return this.orderService.createOrder(orderRequest);
    }
}
