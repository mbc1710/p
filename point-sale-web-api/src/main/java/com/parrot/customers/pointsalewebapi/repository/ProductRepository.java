package com.parrot.customers.pointsalewebapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parrot.customers.pointsalewebapi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query()
    public Product findByProductId(@Param("productId") Integer productId);

    @Query(value = "SELECT p from Product p WHERE p.createDate BETWEEN :startDate AND :endDate")
    public List<Product> findByCreateDateGreaterThanEqualAndLessThanEqual (Date startDate, Date endDate);
    
}
