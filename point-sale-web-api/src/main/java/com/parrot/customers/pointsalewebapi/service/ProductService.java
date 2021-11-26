package com.parrot.customers.pointsalewebapi.service;

import java.util.List;

import com.parrot.customers.pointsalewebapi.model.Product;

public interface ProductService {

    public List<Product> saveProducts (List<Product> productDTOs);

}
