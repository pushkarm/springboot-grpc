package com.example.productservice.respositories;

import com.example.productservice.entities.Product;
import com.example.productservice.respositories.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @Test
    public void shouldGetListOfProducts() {

        ProductRepository productRepository = new ProductRepository();
        List<Product> list = productRepository.list();

        Assertions.assertNotNull(list, "Product list should not be null.");
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void shouldAddProduct() {

        ProductRepository productRepository = new ProductRepository();
        Product product = new Product(1L, "name", 2);
        productRepository.add(product);

        List<Product> list = productRepository.list();

        Assertions.assertNotNull(list, "Product list should not be null.");
        Assertions.assertEquals(1, list.size());

    }

    @Test
    public void shouldThrowExceptionWhenProductNotFound() {

        ProductRepository productRepository = new ProductRepository();

        Assertions.assertThrows(NotFoundException.class, () -> {
            productRepository.isQuantityAvailable(1L, 1);
        });
    }

    @Test
    public void shouldReturnAvailableWhenQuantityIsAvailable() {

        ProductRepository productRepository = new ProductRepository();
        Product product = new Product(1L, "name", 2);
        productRepository.add(product);

        boolean quantityAvailable = productRepository.isQuantityAvailable(1L, 1);

        Assertions.assertTrue(quantityAvailable);
    }

    @Test
    public void shouldReturnUnavailableWhenQuantityIsAvailable() {

        ProductRepository productRepository = new ProductRepository();
        Product product = new Product(1L, "name", 1);
        productRepository.add(product);

        boolean quantityAvailable = productRepository.isQuantityAvailable(1L, 2);

        Assertions.assertFalse(quantityAvailable);
    }
}