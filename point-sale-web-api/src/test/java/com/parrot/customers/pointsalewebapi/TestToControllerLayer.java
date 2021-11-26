package com.parrot.customers.pointsalewebapi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.parrot.customers.pointsalewebapi.controller.OrderControllerTest;
import com.parrot.customers.pointsalewebapi.controller.ReportControllerTest;
import com.parrot.customers.pointsalewebapi.controller.UserControllerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ OrderControllerTest.class, ReportControllerTest.class, UserControllerTest.class })
public class TestToControllerLayer {

}