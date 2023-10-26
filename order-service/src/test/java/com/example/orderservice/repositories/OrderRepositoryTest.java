package com.example.orderservice.repositories;

import com.example.orderservice.entities.Order;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    @Test
    public void shouldSaveOrder() {

        OrderRepository orderRepository = new OrderRepository();
        Order order = new Order(12L, 1);
        orderRepository.save(order);

        Order savedOrder = orderRepository.findById(order.getId());

        Assert.notNull(savedOrder, "Saved order should not be null");
    }
}