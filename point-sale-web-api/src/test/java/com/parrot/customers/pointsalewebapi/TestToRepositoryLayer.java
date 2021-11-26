package com.parrot.customers.pointsalewebapi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.parrot.customers.pointsalewebapi.repository.OrderRepositoryTest;
import com.parrot.customers.pointsalewebapi.repository.ProductRepositoryTest;
import com.parrot.customers.pointsalewebapi.repository.UserRepositoryTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ OrderRepositoryTest.class, ProductRepositoryTest.class, UserRepositoryTest.class })
public class TestToRepositoryLayer {
}