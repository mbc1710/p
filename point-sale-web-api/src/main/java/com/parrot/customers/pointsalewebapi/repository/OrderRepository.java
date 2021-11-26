package com.parrot.customers.pointsalewebapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parrot.customers.pointsalewebapi.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    @Query()
    public Order findByOrderId(@Param("orderId") Integer orderId);
    
}
