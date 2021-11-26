package com.parrot.customers.pointsalewebapi.service;

import java.util.List;

import com.parrot.customers.pointsalewebapi.dto.OrderDTO;

public interface OrderService {

    public List<OrderDTO> saveOrder (List<OrderDTO> orderDTOlist, String email);

    public OrderDTO getOrderById (Integer orderId);
}
