package com.parrot.customers.pointsalewebapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parrot.customers.pointsalewebapi.dto.OrderDTO;
import com.parrot.customers.pointsalewebapi.exceptions.ServiceException;
import com.parrot.customers.pointsalewebapi.model.Order;
import com.parrot.customers.pointsalewebapi.model.User;
import com.parrot.customers.pointsalewebapi.repository.OrderRepository;
import com.parrot.customers.pointsalewebapi.repository.UserRepository;
import com.parrot.customers.pointsalewebapi.service.OrderService;
import com.parrot.customers.pointsalewebapi.service.ProductService;
import com.parrot.customers.pointsalewebapi.service.converter.OrderConverter;
import com.parrot.customers.pointsalewebapi.util.StreamUtils;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductService productService;
    
    /**
     * Funcion para guardar una lista de ordenes de un usuario
     */
    @Override
    public List<OrderDTO> saveOrder (List<OrderDTO> ordersDTO, String email) {
        List<OrderDTO> response = null;
        try {
            Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
            if(user.isPresent()) {
                response = OrderConverter.convertToOrderDTOFromOrder(saveOrdersAndProducts(user.get(), OrderConverter.convertToOrderFromOrderDTO(ordersDTO)));
            }else {
                new ServiceException("The user mail " + email +" not exists");
            }
        } catch (Exception ex) {
            LOGGER.error("Error in save the orders: " + ordersDTO.toString()+ " " + ex.getCause(), ex);
            throw new ServiceException("Error in save the orders: " + ordersDTO.toString()+ " " + ex.getCause(), ex);
        }
        return response;
    }

    /**
     * Funcion que recibe la lista de ordenes de un usuario y las persiste en BD
     * @param user
     * @param orders
     * @return
     */
    private List<Order> saveOrdersAndProducts (User user, List<Order> orders) {
        StreamUtils.asStream(orders).forEach(order -> {
            if(order.getProductsList().size() > 0) {
                order.setUser(user);
                orderRepository.saveAndFlush(order);
                StreamUtils.asStream(order.getProductsList()).forEach(product -> product.setOrder(order));
                productService.saveProducts(order.getProductsList());
            }
        });
        return orders;
    }

    /**
     * Funcion para obtener la informacion de una orden en especifico
     */
    @Override
    public OrderDTO getOrderById (Integer orderId) {
        OrderDTO response;
        try {
            response = OrderConverter.convertToOrderDTOFromOrder(orderRepository.findByOrderId(orderId));
        } catch (Exception ex) {
            LOGGER.error("Error in get information of order: " + orderId+ " " + ex.getCause(), ex);
            throw new ServiceException("Error in get information of order: " + orderId+ " " + ex.getCause(), ex);
        }
        return response;
    }
}
