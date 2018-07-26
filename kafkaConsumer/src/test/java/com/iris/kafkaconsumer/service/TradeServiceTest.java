package com.iris.kafkaconsumer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.iris.kafkaconsumer.dao.TradeDAO;
import com.iris.kafkaconsumer.dao.TradeDAOTest;
import com.iris.kafkaconsumer.model.Trade;
import com.iris.kafkaconsumer.serviceImpl.TradeServiceImpl;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
@RunWith(SpringRunner.class)
public class TradeServiceTest {

	private static final Logger logger = Logger.getLogger(TradeServiceTest.class.getName());
	
	@MockBean
	private TradeDAO tradeDao;

	private static final String SECURITY = "IRIS";

	@Autowired
	private TradeService tradeService;

	@TestConfiguration
	static class TradeServiceTestContextConfiguration {

		@Bean
		public TradeService employeeService() {
			return new TradeServiceImpl();
		}
	}

	@Test
	public void contextLoads() throws Exception {
		when(tradeDao.getTradeListBySecurity(SECURITY)).thenReturn(getTradeList());

		List<Trade> tradeList = tradeService.getTradeListBySecurity(SECURITY);

		assertThat(getTradeList());
	}

	public List<Trade> getTradeList() {
		List<Trade> tradeList = new ArrayList<>();
		try {
			Trade t = new Trade();
			t.setBroker("IndiaBulls");
			t.setCurrency("INR");
			t.setInstrumentType("Equity");
			t.setSecurity("IRIS");
			t.setTradeDate(new SimpleDateFormat("dd-MM-yyyy").parse("05-07-2018"));
			t.setTradeTime(new SimpleDateFormat("HH:mm:ss").parse("08:02:53"));
			t.setTradeId(14036);
			t.setTradePrice(103.93);

			Trade t1 = new Trade();
			t1.setBroker("CITI");
			t1.setCurrency("INR");
			t1.setInstrumentType("Equity");
			t1.setSecurity("IRIS");
			t1.setTradeDate(new SimpleDateFormat("dd-MM-yyyy").parse("05-07-2018"));
			t1.setTradeTime(new SimpleDateFormat("HH:mm:ss").parse("08:38:29"));
			t1.setTradeId(16136);
			t1.setTradePrice(103.86);

			tradeList.add(t);
			tradeList.add(t1);

		} catch (ParseException e) {
			e.printStackTrace();
			logger.log(Level.WARNING, e.getMessage());
		}
		return tradeList;
	}

}
