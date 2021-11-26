package com.parrot.customers.pointsalewebapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parrot.customers.pointsalewebapi.exceptions.ServiceException;
import com.parrot.customers.pointsalewebapi.model.Product;
import com.parrot.customers.pointsalewebapi.repository.ProductRepository;
import com.parrot.customers.pointsalewebapi.service.ProductService;
import com.parrot.customers.pointsalewebapi.util.StreamUtils;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    /**
     * Funcion para guardar una lista de productos
     */
    @Override
    public List<Product> saveProducts (List<Product> productList) {
        List<Product> response;
        try {
            List<Product> productListFinal = new ArrayList<>();
            Map<String, List<Product>> mapProducts = StreamUtils.asStream(productList).collect(Collectors.groupingBy(Product::getName));
            mapProducts.forEach((productName, listProduct) -> {
                if(listProduct.size() > 1) {
                    Product product = StreamUtils.asStream(listProduct).findFirst().get();
                    product.setAmount(StreamUtils.asStream(listProduct).flatMapToInt(p -> IntStream.of(p.getAmount())).sum());
                    productListFinal.add(product);
                }else {
                    productListFinal.addAll(listProduct);
                }
            });
            response = productRepository.saveAll(productListFinal);
        } catch (Exception ex) {
            LOGGER.error("Error in save the products: " + productList.toString()+ " " + ex.getCause(), ex);
            throw new ServiceException("Error in save the products: " + productList.toString()+ " " + ex.getCause(), ex);
        }
        return response;
    }

}
