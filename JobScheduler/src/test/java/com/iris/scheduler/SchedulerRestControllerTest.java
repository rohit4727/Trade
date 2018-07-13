package com.iris.scheduler;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.iris.scheduler.constants.IControllerConstants;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SchedulerApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SchedulerRestControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext wac;
	
	@Before
	public void setup() {
		 this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void verifyAllScheduleJobList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(IControllerConstants.GET_ALL_JOB_SCHEDULE_DETAILS).accept(MediaType.APPLICATION_JSON)).andReturn();
		ModelAndView model = result.getModelAndView();
		System.out.println(result.toString());
	}

}
