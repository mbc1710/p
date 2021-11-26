package com.parrot.customers.pointsalewebapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parrot.customers.pointsalewebapi.dto.ReportDTO;
import com.parrot.customers.pointsalewebapi.exceptions.ServiceException;
import com.parrot.customers.pointsalewebapi.model.Product;
import com.parrot.customers.pointsalewebapi.repository.ProductRepository;
import com.parrot.customers.pointsalewebapi.service.ReportService;
import com.parrot.customers.pointsalewebapi.util.StreamUtils;
import com.parrot.customers.pointsalewebapi.util.Utils;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    /**
     * Funcion para generar el reporte de productos vendidos por rango de fechas
     */
    @Override
    public List<ReportDTO> getReportByRangeDate (String startDate, String endDate) {
        List<ReportDTO> response = new ArrayList<>();
        try {
            List<Product> productsList = productRepository.findByCreateDateGreaterThanEqualAndLessThanEqual(Utils.parseStringToDate(startDate), Utils.parseStringToDate(endDate));
            Map<String, List<Product>> mapProducts = StreamUtils.asStream(productsList).collect(Collectors.groupingBy(Product::getName));
            mapProducts.forEach((productName, listProduct) -> {
                Integer cantidad = StreamUtils.asStream(listProduct).flatMapToInt(product -> IntStream.of(product.getAmount())).sum();
                Double precio = StreamUtils.asStream(listProduct).flatMapToDouble(product -> DoubleStream.of(product.getUnitPrice())).sum();
                response.add(ReportDTO.builder().productName(productName).totalAmount(cantidad).totalPrice(precio).build());
            });
        } catch (Exception ex) {
            LOGGER.error("Error in get information of products: "+ " " + ex.getCause(), ex);
            throw new ServiceException("Error in get information of products: "+  ex.getCause(), ex);
        }
        return response;
    }
}
