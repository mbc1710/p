package com.parrot.customers.pointsalewebapi.service;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import com.parrot.customers.pointsalewebapi.Order;
import com.parrot.customers.pointsalewebapi.model.Product;

public class ProductServiceImplTest extends BaseServiceTest {

    @Test
    @Order(order = 1)
    public void saveProducts () {
        when(productRepository.saveAll(isA(List.class))).thenReturn(productList);
        List<Product> result = productServiceImpl.saveProducts(productList);
        assertNotEquals(0, result.size());
    }

}
