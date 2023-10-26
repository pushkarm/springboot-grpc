package com.example.productservice.grpc;

import com.example.common.grpc.CheckProductAvailabilityRequest;
import com.example.common.grpc.CheckProductAvailabilityResponse;
import com.example.common.grpc.ProductGrpcServiceGrpc;
import com.example.productservice.respositories.ProductRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ProductGrpcService extends ProductGrpcServiceGrpc.ProductGrpcServiceImplBase {

    final ProductRepository productRepository;

    public ProductGrpcService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void checkProduct(CheckProductAvailabilityRequest request, StreamObserver<CheckProductAvailabilityResponse> responseObserver) {

        try {
            boolean quantityAvailable = this.productRepository.isQuantityAvailable(request.getProductId(), request.getQuantity());
            CheckProductAvailabilityResponse response = CheckProductAvailabilityResponse.newBuilder()
                    .setAvailable(quantityAvailable).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception ex) {
            Status status = Status.fromCode(Status.Code.FAILED_PRECONDITION).augmentDescription(ex.getMessage());
            responseObserver.onError(status.asRuntimeException());
        }
    }
}
