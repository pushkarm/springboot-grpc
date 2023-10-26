package com.example.orderservice.services;

import com.example.common.grpc.CheckProductAvailabilityRequest;
import com.example.common.grpc.CheckProductAvailabilityResponse;
import com.example.common.grpc.ProductGrpcServiceGrpc;
import com.example.orderservice.entities.Order;
import com.example.orderservice.exceptions.ProductNotAvailableException;
import com.example.orderservice.payload.OrderRequest;
import com.example.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    final OrderRepository orderRepository;

    final ProductGrpcServiceGrpc.ProductGrpcServiceBlockingStub productGrpcServiceBlockingStub;

    public OrderService(OrderRepository orderRepository,
                        ProductGrpcServiceGrpc.ProductGrpcServiceBlockingStub productGrpcServiceBlockingStub) {
        this.orderRepository = orderRepository;
        this.productGrpcServiceBlockingStub = productGrpcServiceBlockingStub;
    }

    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order(orderRequest.productId(), orderRequest.quantity());
        CheckProductAvailabilityResponse checkProductAvailabilityResponse = null;
        try {
            CheckProductAvailabilityRequest re = CheckProductAvailabilityRequest.newBuilder()
                    .setProductId(orderRequest.productId())
                    .setQuantity(orderRequest.quantity()).build();
            checkProductAvailabilityResponse
                    = productGrpcServiceBlockingStub.checkProduct(re);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        if (checkProductAvailabilityResponse.getAvailable()) {
            this.orderRepository.save(order);
            return order;
        }

        throw new ProductNotAvailableException("Product not available.");
    }
}
