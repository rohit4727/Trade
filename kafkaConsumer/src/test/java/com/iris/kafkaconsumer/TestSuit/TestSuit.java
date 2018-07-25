/**
 * @Date : 20-Jul-2018
 * @Author : Rohit Chauhan
 */
package com.iris.kafkaconsumer.TestSuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.iris.kafkaconsumer.controller.TradeController;
import com.iris.kafkaconsumer.dao.TradeDAOTest;
import com.iris.kafkaconsumer.integration.TradeIntegrationTest;
import com.iris.kafkaconsumer.service.TradeServiceTest;

//Create the Test Suite from diffrent Junit tests 
@RunWith(Suite.class)
@Suite.SuiteClasses({ TradeIntegrationTest.class, TradeController.class, TradeServiceTest.class, TradeDAOTest.class })
public class TestSuit {

}
