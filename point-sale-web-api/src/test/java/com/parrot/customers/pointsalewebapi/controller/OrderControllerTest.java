package com.parrot.customers.pointsalewebapi.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import static org.mockito.ArgumentMatchers.isA;
import org.junit.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.parrot.customers.pointsalewebapi.JsonUtil;
import com.parrot.customers.pointsalewebapi.Order;

public class OrderControllerTest extends BaseControllerTest {

    @Test
    @Order(order = 1)
    public void saveOrder() throws Exception {
        final String url = new StringBuilder().append(contextPath).append("/order/save/"+mail).toString();
        when(orderServiceImpl.saveOrder(isA(List.class), isA(String.class))).thenReturn(this.orderDTOList);
        final RequestBuilder request = MockMvcRequestBuilders.post(url).accept(contentType).contentType(contentType).content(JsonUtil.asJsonString(orderDTOList));
        super.MOCK.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(200))
        .andExpect(jsonPath("$.responseBody.[0].orderId").value(orderDTOList.get(0).getOrderId()))
                .andDo(print()).andReturn();
    }
    
    @Test
    @Order(order = 2)
    public void getOrderById() throws Exception {
        final String url = new StringBuilder().append(contextPath).append("/order/finByOrderId/"+orderId).toString();
        when(orderServiceImpl.getOrderById(isA(Integer.class))).thenReturn(this.orderDTO);
        final RequestBuilder request = MockMvcRequestBuilders.get(url).accept(contentType).contentType(contentType);
        super.MOCK.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(200))
        .andExpect(jsonPath("$.responseBody.orderId").value(orderDTO.getOrderId())).andDo(print()).andReturn();
    }

}
