### Order Service

This is order service. We only have a single endpoint which act as order placement api.

We check with product service if the product is available or not then accordingly place the order.

### Grpc Spring Boot Starter

We are using grpc client spring boot starter dependency

```
 implementation group: 'net.devh', name: 'grpc-client-spring-boot-starter', version: '2.15.0.RELEASE'
```

#### Grpc Client Configuration

We update the application properties to define the grpc server endpoint for particular grpc client to connect
e.g. Here our server is running on 9090 port

```
grpc.client.product-service.address=static://localhost:9090
grpc.client.product-service.negotiation-type=plaintext
```

