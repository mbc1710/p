package com.parrot.customers.pointsalewebapi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestToControllerLayer.class, TestToServiceLayer.class, TestToRepositoryLayer.class })
public class TestToAllLayer {

}