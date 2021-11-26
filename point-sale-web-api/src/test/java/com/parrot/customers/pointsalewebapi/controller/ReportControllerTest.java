package com.parrot.customers.pointsalewebapi.controller;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.parrot.customers.pointsalewebapi.Order;

public class ReportControllerTest extends BaseControllerTest {

    @Test
    @Order(order = 1)
    public void getReportByRangeDate() throws Exception {
        final String url = new StringBuilder().append(contextPath).append("/report/getReport/26-11-2021/27-11-2021").toString();
        when(reportServiceImpl.getReportByRangeDate(isA(String.class), isA(String.class))).thenReturn(this.reportDTOList);
        final RequestBuilder request = MockMvcRequestBuilders.get(url).accept(contentType).contentType(contentType);
        super.MOCK.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(200))
        .andExpect(jsonPath("$.responseBody.[0].productName").value(reportDTOList.get(0).getProductName())).andDo(print()).andReturn();
    }

}
