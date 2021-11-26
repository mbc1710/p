package com.parrot.customers.pointsalewebapi;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.parrot.customers.pointsalewebapi.dto.OrderDTO;
import com.parrot.customers.pointsalewebapi.dto.ProductDTO;
import com.parrot.customers.pointsalewebapi.dto.ReportDTO;
import com.parrot.customers.pointsalewebapi.dto.UserDTO;
import com.parrot.customers.pointsalewebapi.model.Order;
import com.parrot.customers.pointsalewebapi.model.Product;
import com.parrot.customers.pointsalewebapi.model.User;

import ch.qos.logback.classic.BasicConfigurator;

public abstract class BaseTest {

    public BaseTest() {
        this.setup();
    }

    protected final static Logger LOGGER = LoggerFactory.getLogger(BasicConfigurator.class);

     protected String contextPath = "/pointsale";
     protected String mail = "maciel.benitez@test.com";
     protected Integer orderId = 1;
     protected OrderDTO orderDTO = new OrderDTO();
     protected ProductDTO productDTO = new ProductDTO();
     protected ReportDTO reportDTO = new ReportDTO();
     protected UserDTO userDTO = new UserDTO();
     protected List<OrderDTO> orderDTOList = new ArrayList<>();
     protected List<ProductDTO> productDTOList = new ArrayList<>();
     protected List<ReportDTO> reportDTOList = new ArrayList<>();
     protected List<UserDTO> userDTOList = new ArrayList<>();
     protected Order order = new Order();
     protected Product product = new Product();
     protected User user = new User();
     protected List<Order> orderList = new ArrayList<>();
     protected List<Product> productList = new ArrayList<>();
     protected List<User> userList = new ArrayList<>();

    private void setup () {
        this.setMemoryProductDTO();
        this.setMemoryOrderDTO();
        this.setMemoryUserDTO();
        this.setMemoryReportDTO();
        this.setMemoryProduct();
        this.setMemoryOrder();
        this.setMemoryUser();
        this.setMemoryReportListDTO();
    }

    private void setMemoryReportListDTO () {
        List<ReportDTO> reportDTOList = new ArrayList<>();
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setProductName("producto1");
        reportDTO.setTotalAmount(10);
        reportDTO.setTotalPrice(150.5);
        reportDTOList.add(reportDTO);
        this.reportDTOList = reportDTOList;
    }

    private void setMemoryOrder () {
        Order order = new Order();
        order.setOrderId(1);
        order.setClient("cliente1");
        order.setTotal(20.5);
        order.setUser(this.user);
        order.setProductsList(productList);
        this.order = order;
        this.orderList.add(order);
        Order order2 = new Order();
        order2.setOrderId(2);
        order2.setClient("cliente2");
        order2.setTotal(10.5);
        order2.setUser(this.user);
        order2.setProductsList(productList);
        this.orderList.add(order2);
    }

    private void setMemoryProduct () {
        Product product = new Product();
        product.setProductId(1);
        product.setName("producto1");
        product.setAmount(2);
        product.setUnitPrice(10.5);
        product.setOrder(order);
        this.product = product;
        this.productList.add(product);
        Product product2 = new Product();
        product2.setProductId(2);
        product2.setName("producto2");
        product2.setAmount(5);
        product2.setUnitPrice(5.5);
        product2.setOrder(order);
        this.productList.add(product2);
    }

    private void setMemoryUser () {
        User user = new User();
        user.setUserId(1);
        user.setEmail("mail.prueba@test.com");
        user.setName("Nombre");
        user.setOrdersList(orderList);
        this.user = user;
        this.userList.add(user);
    }
    
    private void setMemoryOrderDTO () {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(1);
        orderDTO.setClient("cliente1");
        orderDTO.setTotal(20.5);
        orderDTO.setProductsDTOList(productDTOList);
        this.orderDTO = orderDTO;
        this.orderDTOList.add(orderDTO);
        OrderDTO orderDTO2 = new OrderDTO();
        orderDTO2.setOrderId(2);
        orderDTO2.setClient("cliente2");
        orderDTO2.setTotal(10.5);
        orderDTO2.setProductsDTOList(productDTOList);
        this.orderDTOList.add(orderDTO2);
    }

    private void setMemoryProductDTO () {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(1);
        productDTO.setName("producto1");
        productDTO.setAmount(2);
        productDTO.setUnitPrice(10.5);
        this.productDTO = productDTO;
        this.productDTOList.add(productDTO);
        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setProductId(2);
        productDTO2.setName("producto2");
        productDTO2.setAmount(4);
        productDTO2.setUnitPrice(3.5);
        this.productDTOList.add(productDTO2);
    }

    private void setMemoryUserDTO () {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1);
        userDTO.setEmail("mail.prueba@test.com");
        userDTO.setName("Nombre");
        userDTO.setOrdersDTOList(orderDTOList);
        this.userDTO = userDTO;
        this.userDTOList.add(userDTO);
    }
    
    protected void setMemoryReportDTO () {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setProductName("producto1");
        reportDTO.setTotalAmount(3);
        reportDTO.setTotalPrice(22.50);
        this.reportDTO = reportDTO;
    }

}
