package com.parrot.customers.pointsalewebapi.repository;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.parrot.customers.pointsalewebapi.Order;

public class UserRepositoryTest extends BaseRepositoryTest {

    @Test
    @Order(order = 1)
    public void findByEmail () {
         when(userRepository.findByEmail(isA(String.class))).thenReturn(user);
    }
}
