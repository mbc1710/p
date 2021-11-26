package com.parrot.customers.pointsalewebapi.service;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import com.parrot.customers.pointsalewebapi.Order;
import com.parrot.customers.pointsalewebapi.dto.OrderDTO;

public class OrderServiceImplTest extends BaseServiceTest {

    @Test
    @Order(order = 1)
    public void saveOrder () {
        when(orderRepository.saveAndFlush(isA(com.parrot.customers.pointsalewebapi.model.Order.class))).thenReturn(order);
        when(userRepository.findByEmail(isA(String.class))).thenReturn(user);
        when(productServiceImpl.saveProducts(isA(List.class))).thenReturn(productDTOList);
        List<OrderDTO> result = orderServiceImpl.saveOrder(orderDTOList, mail);
        assertNotEquals(0, result.size());
    }

    @Test
    @Order(order = 2)
    public void getOrderById () {
        when(orderRepository.findByOrderId(isA(Integer.class))).thenReturn(order);
        OrderDTO result = orderServiceImpl.getOrderById(orderId);
        assertNotEquals(null, result);
    }
}
