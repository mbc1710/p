package com.parrot.customers.pointsalewebapi.service;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import com.parrot.customers.pointsalewebapi.Order;
import com.parrot.customers.pointsalewebapi.dto.UserDTO;
import com.parrot.customers.pointsalewebapi.model.User;

public class UserServiceImplTest extends BaseServiceTest {

    @Test
    @Order(order = 1)
    public void getUserList () {
        when(userRepository.findAll()).thenReturn(userList);
        List<UserDTO> result = userServiceImpl.getUserList();
        assertNotEquals(0, result.size());
    }

    @Test
    @Order(order = 2)
    public void saveUser () {
        when(userRepository.saveAndFlush(isA(User.class))).thenReturn(user);
        UserDTO result = userServiceImpl.saveUser(userDTO);
        assertNotEquals(null, result);
    }
    
    @Test
    @Order(order = 3)
    public void getUserByEmail () {
        when(userRepository.findByEmail(isA(String.class))).thenReturn(user);
        UserDTO result = userServiceImpl.getUserByEmail(mail);
        assertNotEquals(null, result);
    }

}
