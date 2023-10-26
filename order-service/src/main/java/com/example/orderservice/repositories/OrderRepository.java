package com.example.orderservice.repositories;

import com.example.orderservice.entities.Order;
import com.example.orderservice.repositories.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository {

    private List<Order> list;

    public OrderRepository() {
        list = new ArrayList<>();
    }

    public Order save(Order order) {
        this.list.add(order);
        return order;
    }

    public Order findById(UUID id) {
        return this.list.stream().filter(order -> order.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Order not found."));
    }
}
