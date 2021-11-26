package com.parrot.customers.pointsalewebapi.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.isA;
import org.junit.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.parrot.customers.pointsalewebapi.JsonUtil;
import com.parrot.customers.pointsalewebapi.Order;
import com.parrot.customers.pointsalewebapi.dto.UserDTO;

public class UserControllerTest extends BaseControllerTest {

    @Test
    @Order(order = 1)
    public void getUserList() throws Exception {
        final String url = new StringBuilder().append(contextPath).append("/user/list").toString();
        when(userServiceImpl.getUserList()).thenReturn(this.userDTOList);
        final RequestBuilder request = MockMvcRequestBuilders.get(url).accept(contentType).contentType(contentType);
        super.MOCK.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(200))
        .andExpect(jsonPath("$.responseBody.[0].name").value(userDTOList.get(0).getName())).andDo(print()).andReturn();
    }
    
    @Test
    @Order(order = 2)
    public void saveUser() throws Exception {
        final String url = new StringBuilder().append(contextPath).append("/user/save").toString();
        when(userServiceImpl.saveUser(isA(UserDTO.class))).thenReturn(this.userDTO);
        final RequestBuilder request = MockMvcRequestBuilders.post(url).accept(contentType).contentType(contentType).content(JsonUtil.asJsonString(userDTO));
        super.MOCK.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(200))
        .andExpect(jsonPath("$.responseBody.email").value(userDTO.getEmail())).andDo(print()).andReturn();
    }
    
    @Test
    @Order(order = 3)
    public void getUserByEmail() throws Exception {
        final String url = new StringBuilder().append(contextPath).append("/user/finByEmail/"+mail).toString();
        when(userServiceImpl.getUserByEmail(isA(String.class))).thenReturn(this.userDTO);
        final RequestBuilder request = MockMvcRequestBuilders.get(url).accept(contentType).contentType(contentType);
        super.MOCK.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(200))
        .andExpect(jsonPath("$.responseBody.email").value(userDTO.getEmail())).andDo(print()).andReturn();
    }

}
