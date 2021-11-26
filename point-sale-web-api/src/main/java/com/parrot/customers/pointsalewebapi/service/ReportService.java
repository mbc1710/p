package com.parrot.customers.pointsalewebapi.service;

import java.util.List;

import com.parrot.customers.pointsalewebapi.dto.ReportDTO;

public interface ReportService {

    public List<ReportDTO> getReportByRangeDate (String startDate, String endDate);

}
