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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parrot.customers.pointsalewebapi.constans.Constans;
import com.parrot.customers.pointsalewebapi.dto.OrderDTO;
import com.parrot.customers.pointsalewebapi.exceptions.ServiceException;
import com.parrot.customers.pointsalewebapi.service.OrderService;
import com.parrot.customers.pointsalewebapi.util.Response;

@RestController
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/pointsale")
public class OrderController {

    final static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;
    
    @PostMapping(path = "/order/save/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Response<List<OrderDTO>>> saveOrder(@RequestBody List<OrderDTO> ordersDTO, @PathVariable(name = "email") String email) {
        Response<List<OrderDTO>> response;
        try {
            response = new Response<>(orderService.saveOrder(ordersDTO, email), Constans.MESSAGE_SAVE_SUCCESS);
        } catch (ServiceException ex) {
            LOGGER.error(ex.getMessage(), ex);
            response = new Response<>(null, ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
    
    @GetMapping(path = "/order/finByOrderId/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Response<OrderDTO>> getOrderById(@PathVariable(name = "orderId") Integer orderId) {
        Response<OrderDTO> response;
        try {
            response = new Response<>(orderService.getOrderById(orderId));
        } catch (ServiceException ex) {
            LOGGER.error(ex.getMessage(), ex);
            response = new Response<>(null, ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
