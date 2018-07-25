package com.iris.kafkaconsumer.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.iris.kafkaconsumer.daoImpl.ConsumerDAOImpl;
import com.iris.kafkaconsumer.model.Trade;
import com.iris.kafkaconsumer.repository.TradeRepository;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class TradeDAOTest {

	static Logger logger = Logger.getLogger(TradeDAOTest.class.getName()); 
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TradeRepository tradeRepository;

	private static final String SECURITY = "IRIS";

	@Test
	public void whenFindSecurity() {
		tradeRepository.deleteAll();
		// given
		List<Trade> tradeList = getTradeList();
		tradeList.stream().filter(t -> {
			entityManager.persist(t);
			return true;
		}).count();

		// when
		List<Trade> found = tradeRepository.getTradesBySecurity(SECURITY);

		// then
		assertThat(found.get(0).getTradeId()).isEqualTo(tradeList.get(1).getTradeId());
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
			//e.printStackTrace();
			logger.log(Level.WARNING, e.getMessage());
		}
		return tradeList;
	}

}
