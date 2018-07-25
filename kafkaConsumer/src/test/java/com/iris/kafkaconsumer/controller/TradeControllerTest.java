package com.iris.kafkaconsumer.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.iris.kafkaconsumer.model.Trade;
import com.iris.kafkaconsumer.service.TradeService;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private TradeService tradeService;

	private static final String SECURITY = "IRIS";

	@Autowired
	private WebApplicationContext context;

	@Before
	public void intialize() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void contextLoads() throws Exception {
		when(tradeService.getTradeListBySecurity(SECURITY)).thenReturn(getTradeList());

		mockMvc.perform(get("/Trade/" + SECURITY).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				// .andExpect(jsonPath("$.", matcher))
				.andDo(print());
		verify(tradeService).getTradeListBySecurity(SECURITY);
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
		}
		return tradeList;
	}

}
