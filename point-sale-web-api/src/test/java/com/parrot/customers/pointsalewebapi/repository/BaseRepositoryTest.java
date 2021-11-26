package com.parrot.customers.pointsalewebapi.repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.parrot.customers.pointsalewebapi.BaseTest;
import com.parrot.customers.pointsalewebapi.OrderedRunner;

@RunWith(OrderedRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseRepositoryTest extends BaseTest {

    @MockBean
    protected OrderRepository orderRepository;
    
    @MockBean
    protected ProductRepository productRepository;
    
    @MockBean
    protected UserRepository userRepository;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    
    @Before
    public void setup () {
        MockitoAnnotations.initMocks(this);
    }

}
