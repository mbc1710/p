package com.parrot.customers.pointsalewebapi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.parrot.customers.pointsalewebapi.service.OrderServiceImplTest;
import com.parrot.customers.pointsalewebapi.service.ProductServiceImplTest;
import com.parrot.customers.pointsalewebapi.service.ReportServiceImplTest;
import com.parrot.customers.pointsalewebapi.service.UserServiceImplTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ OrderServiceImplTest.class, ProductServiceImplTest.class, ReportServiceImplTest.class, UserServiceImplTest.class })
public class TestToServiceLayer {
}