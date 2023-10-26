package com.example.orderservice.services;

import com.example.common.grpc.CheckProductAvailabilityResponse;
import com.example.common.grpc.ProductGrpcServiceGrpc;
import com.example.orderservice.entities.Order;
import com.example.orderservice.exceptions.ProductNotAvailableException;
import com.example.orderservice.payload.OrderRequest;
import com.example.orderservice.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductGrpcServiceGrpc.ProductGrpcServiceBlockingStub productGrpcServiceBlockingStub;

    @Test
    public void shouldCreateOrder() {

        CheckProductAvailabilityResponse response = CheckProductAvailabilityResponse.newBuilder().setAvailable(true).build();

        Mockito.when(productGrpcServiceBlockingStub.checkProduct(Mockito.any())).thenReturn(response);

        OrderRequest orderRequest = new OrderRequest(12L, 1);

        OrderService orderService = new OrderService(orderRepository, productGrpcServiceBlockingStub);
        orderService.createOrder(orderRequest);

        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);

        Mockito.verify(orderRepository, Mockito.times(1)).save(orderArgumentCaptor.capture());
        Order captorValue = orderArgumentCaptor.getValue();

        Assertions.assertEquals(orderRequest.productId(), captorValue.getProductId());

    }

    @Test
    public void shouldThrowExceptionWhenProductNotAvailableWhileCreateOrder() {

        CheckProductAvailabilityResponse response = CheckProductAvailabilityResponse.newBuilder().setAvailable(false).build();
        Mockito.when(productGrpcServiceBlockingStub.checkProduct(Mockito.any())).thenReturn(response);

        OrderService orderService = new OrderService(orderRepository, productGrpcServiceBlockingStub);

        OrderRequest orderRequest = new OrderRequest(12L, 1);

        Assertions.assertThrows(ProductNotAvailableException.class, () -> {
            orderService.createOrder(orderRequest);
        });
    }
}