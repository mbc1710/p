package com.parrot.customers.pointsalewebapi.repository;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;

import com.parrot.customers.pointsalewebapi.Order;

public class ProductRepositoryTest extends BaseRepositoryTest {

    @Test
    @Order(order = 1)
    public void findByProductId () {
         when(productRepository.findByProductId(isA(Integer.class))).thenReturn(product);
    }
    
    @Test
    @Order(order = 2)
    public void findByCreateDateGreaterThanEqualAndLessThanEqual () {
         when(productRepository.findByCreateDateGreaterThanEqualAndLessThanEqual(isA(Date.class),isA(Date.class))).thenReturn(productList);
    }


}
