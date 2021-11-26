package com.parrot.customers.pointsalewebapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parrot.customers.pointsalewebapi.dto.ReportDTO;
import com.parrot.customers.pointsalewebapi.exceptions.ServiceException;
import com.parrot.customers.pointsalewebapi.service.ReportService;
import com.parrot.customers.pointsalewebapi.util.Response;

@RestController
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/pointsale")
public class ReportController {

    final static Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;
    
    @GetMapping(path = "/report/getReport/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Response<List<ReportDTO>>> getReportByRangeDate(@PathVariable(name = "startDate") String startDate, @PathVariable(name = "endDate") String endDate) {
        Response<List<ReportDTO>> response;
        try {
            response = new Response<>(reportService.getReportByRangeDate(startDate, endDate));
        } catch (ServiceException ex) {
            LOGGER.error(ex.getMessage(), ex);
            response = new Response<>(null, ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
