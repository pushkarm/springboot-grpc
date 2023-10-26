## Product Service

This is product service which list all the products. It will act as grpc server where order service will check if the product quantity is available.

We have added some dummy data for testing.

### Grpc Spring Boot Starter

We are using grpc server spring boot starter dependency

```
 implementation group: 'net.devh', name: 'grpc-server-spring-boot-starter', version: '2.15.0.RELEASE'
```

We have to implement the method we define in the proto. 

### Grpc Server Configuration

We update the application properties to define the port on which grpc server will run 
e.g. Here our server is running on 9090 port

```
grpc.server.port=${PRODUCT_SVC_GRPC_SERVER_PORT:9090}
```
