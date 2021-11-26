package com.parrot.customers.pointsalewebapi.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.parrot.customers.pointsalewebapi.BaseTest;
import com.parrot.customers.pointsalewebapi.OrderedRunner;
import com.parrot.customers.pointsalewebapi.repository.OrderRepository;
import com.parrot.customers.pointsalewebapi.repository.ProductRepository;
import com.parrot.customers.pointsalewebapi.repository.UserRepository;
import com.parrot.customers.pointsalewebapi.service.impl.OrderServiceImpl;
import com.parrot.customers.pointsalewebapi.service.impl.ProductServiceImpl;
import com.parrot.customers.pointsalewebapi.service.impl.ReportServiceImpl;
import com.parrot.customers.pointsalewebapi.service.impl.UserServiceImpl;

@RunWith(OrderedRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseServiceTest extends BaseTest {

    @Mock
    protected OrderRepository orderRepository;
    
    @Mock
    protected ProductRepository productRepository;
    
    @Mock
    protected UserRepository userRepository;

    @InjectMocks
    protected OrderServiceImpl orderServiceImpl;

    @InjectMocks
    protected ProductServiceImpl productServiceImpl;

    @InjectMocks
    protected ReportServiceImpl reportServiceImpl;
    
    @InjectMocks
    protected UserServiceImpl userServiceImpl;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

}
