package com.example.productservice.grpc;

import com.example.common.grpc.CheckProductAvailabilityRequest;
import com.example.common.grpc.CheckProductAvailabilityResponse;
import com.example.productservice.respositories.ProductRepository;
import com.example.productservice.respositories.exceptions.NotFoundException;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductGrpcServiceTest {

    @Mock
    ProductRepository productRepository;

    @Test
    public void shouldCheckProductReturnAvailable() {

        Mockito.when(productRepository.isQuantityAvailable(1L, 1)).thenReturn(true);

        CheckProductAvailabilityRequest availabilityRequest = CheckProductAvailabilityRequest.newBuilder()
                .setProductId(1L).setQuantity(1).build();
        StreamObserver<CheckProductAvailabilityResponse> responseObserver = Mockito.mock(StreamObserver.class);

        ProductGrpcService productGrpcService = new ProductGrpcService(productRepository);
        productGrpcService.checkProduct(availabilityRequest, responseObserver);

        ArgumentCaptor<CheckProductAvailabilityResponse> responseArgumentCaptor = ArgumentCaptor
                .forClass(CheckProductAvailabilityResponse.class);
        Mockito.verify(responseObserver, Mockito.times(1))
                .onNext(responseArgumentCaptor.capture());

        CheckProductAvailabilityResponse responseArgumentCaptorValue = responseArgumentCaptor.getValue();
        Assertions.assertTrue(responseArgumentCaptorValue.getAvailable());
    }

    @Test
    public void shouldCheckProductReturnUnavailable() {

        Mockito.when(productRepository.isQuantityAvailable(1L, 1)).thenReturn(false);

        CheckProductAvailabilityRequest availabilityRequest = CheckProductAvailabilityRequest.newBuilder()
                .setProductId(1L).setQuantity(1).build();
        StreamObserver<CheckProductAvailabilityResponse> responseObserver = Mockito.mock(StreamObserver.class);

        ProductGrpcService productGrpcService = new ProductGrpcService(productRepository);
        productGrpcService.checkProduct(availabilityRequest, responseObserver);

        ArgumentCaptor<CheckProductAvailabilityResponse> responseArgumentCaptor = ArgumentCaptor
                .forClass(CheckProductAvailabilityResponse.class);
        Mockito.verify(responseObserver, Mockito.times(1))
                .onNext(responseArgumentCaptor.capture());

        CheckProductAvailabilityResponse responseArgumentCaptorValue = responseArgumentCaptor.getValue();
        Assertions.assertFalse(responseArgumentCaptorValue.getAvailable());
    }

    @Test
    public void shouldCheckProductReturnError() {

        Mockito.when(productRepository.isQuantityAvailable(1L, 1))
                .thenThrow(new NotFoundException("Product not found"));

        CheckProductAvailabilityRequest availabilityRequest = CheckProductAvailabilityRequest.newBuilder()
                .setProductId(1L).setQuantity(1).build();
        StreamObserver<CheckProductAvailabilityResponse> responseObserver = Mockito.mock(StreamObserver.class);

        ProductGrpcService productGrpcService = new ProductGrpcService(productRepository);
        productGrpcService.checkProduct(availabilityRequest, responseObserver);

        ArgumentCaptor<Throwable> responseArgumentCaptor = ArgumentCaptor
                .forClass(Throwable.class);
        Mockito.verify(responseObserver, Mockito.times(1))
                .onError(responseArgumentCaptor.capture());

        Throwable throwable = responseArgumentCaptor.getValue();
        Assertions.assertEquals("FAILED_PRECONDITION: Product not found", throwable.getMessage());
    }
}