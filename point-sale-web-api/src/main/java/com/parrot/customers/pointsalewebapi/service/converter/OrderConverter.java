package com.parrot.customers.pointsalewebapi.service.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.parrot.customers.pointsalewebapi.dto.OrderDTO;
import com.parrot.customers.pointsalewebapi.model.Order;

public class OrderConverter {

    public static List<OrderDTO> convertToOrderDTOFromOrder (Iterable<Order> orderList) {
        return StreamSupport.stream(orderList.spliterator(), false)
                .map(mapper -> convertToOrderDTOFromOrder(mapper))
                .collect(Collectors.toList());
    }

    public static OrderDTO convertToOrderDTOFromOrder (Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .client(order.getClient())
                .total(order.getTotal())
                .productsDTOList(ProductConverter.convertToProductDTOFromProduct(order.getProductsList()))
                .build();
    }

    public static Order convertToOrderFromOrderDTO (OrderDTO orderDTO) {
        return Order.builder()
                .client(orderDTO.getClient())
                .total(orderDTO.getTotal())
                .createDate(new Date())
                .productsList(orderDTO.getProductsDTOList().size() > 0 ? ProductConverter.convertToProductFromProductDTO(orderDTO.getProductsDTOList()) : new ArrayList<>())
                .build();
    }

    public static List<Order> convertToOrderFromOrderDTO (Iterable<OrderDTO> orderDTOList) {
        return StreamSupport.stream(orderDTOList.spliterator(), false)
                .map(mapper -> convertToOrderFromOrderDTO(mapper))
                .collect(Collectors.toList());
    }
}
