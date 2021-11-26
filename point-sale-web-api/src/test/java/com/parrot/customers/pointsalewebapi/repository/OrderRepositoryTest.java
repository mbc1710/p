package com.parrot.customers.pointsalewebapi.repository;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.parrot.customers.pointsalewebapi.Order;

public class OrderRepositoryTest extends BaseRepositoryTest {

    @Test
    @Order(order = 1)
    public void findByOrderId () {
         when(orderRepository.findByOrderId(isA(Integer.class))).thenReturn(order);
    }
}
