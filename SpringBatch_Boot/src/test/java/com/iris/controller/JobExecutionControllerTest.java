package com.iris.controller;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iris.mvc.service.SpringBatchService;

@RunWith(SpringRunner.class)
@WebMvcTest(JobExecutionController.class)
public class JobExecutionControllerTest {

	private static final Logger log = LoggerFactory.getLogger(JobExecutionControllerTest.class);

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SpringBatchService batchService;

	@MockBean
	private DataSource dataSource;

	@Test
	public void testJobExecutionController() throws Exception {
		Mockito.when(batchService.runJob(Mockito.anyLong())).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/runjob/25").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		log.debug("result", result.getResponse());

		String expected = "{\"status\":true}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testService() {
		
	}
}
