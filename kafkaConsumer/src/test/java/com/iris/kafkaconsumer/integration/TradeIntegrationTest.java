package com.iris.kafkaconsumer.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.iris.kafkaconsumer.constants.IConstants;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class TradeIntegrationTest {

	private MockMvc mockMvc;

	private static final String SECURITY = "IRIS";
	private static final String WRONG_SECURITY = "WRONG";
	private static final String tradeDate = "05-07-2018";
	private static final String tradeTime = "08:04:07";

	@Autowired
	private WebApplicationContext context;

	@Before
	public void intialize() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void contextLoads() throws Exception {

		mockMvc.perform(get("/Trade/" + SECURITY).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void getSecurityListTest() throws Exception {

		mockMvc.perform(get(IConstants.GET_SECURITY).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void findTradeTest() throws Exception {

		mockMvc.perform(
				get("/Trade/" + SECURITY + "/" + tradeDate + "/" + tradeTime).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void findTradeFailTest() throws Exception {

		mockMvc.perform(
				get("/Trade/" + WRONG_SECURITY + "/" + tradeDate + "/" + tradeTime).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				// .andExpect((ResultMatcher) jsonPath("$.message", "Trade not Found!"))
				.andDo(print());
	}
}
