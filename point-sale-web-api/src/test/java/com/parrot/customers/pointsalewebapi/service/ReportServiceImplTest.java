package com.parrot.customers.pointsalewebapi.service;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.parrot.customers.pointsalewebapi.Order;
import com.parrot.customers.pointsalewebapi.dto.ReportDTO;

public class ReportServiceImplTest extends BaseServiceTest {

    @Test
    @Order(order = 2)
    public void getReportByRangeDate () {
        when(productRepository.findByCreateDateGreaterThanEqualAndLessThanEqual(isA(Date.class),isA(Date.class))).thenReturn(productList);
        List<ReportDTO> result = reportServiceImpl.getReportByRangeDate("26-11-2021", "27-11-2021");
        assertNotEquals(0, result.size());
    }

}
