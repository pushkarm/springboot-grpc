package com.example.orderservice.configs;

import com.example.common.grpc.ProductGrpcServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @GrpcClient("product-service")
    ProductGrpcServiceGrpc.ProductGrpcServiceBlockingStub productGrpcServiceBlockingStub;

    @Bean
    public ProductGrpcServiceGrpc.ProductGrpcServiceBlockingStub productGrpcServiceBlockingStub() {
        return productGrpcServiceBlockingStub;
    }
}
