
package com.iris.scheduler;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.actuate.web.mappings.reactive.RequestMappingConditionsDescription.MediaTypeExpressionDescription;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.iris.scheduler.beans.ResponseBean;
import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.rest.controller.ShedulerRestController;
import com.iris.scheduler.service.JobSchedulerDetailService;
import com.iris.scheduler.service.SchedulerService;

import ch.qos.logback.core.boolex.Matcher;

/**
 * @author anchal.handa
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RunJobTest {

	private MockMvc mockMvc;

	@InjectMocks
	ShedulerRestController schedulerRestController;

	@Mock
	JobSchedulerDetailService jobSchedulerDetailService;

	@Mock
	SchedulerService schedulerService;

	@MockBean
	ResponseBean responseBean;

	@MockBean
	JobScheduler jobScheduler;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(schedulerRestController).build();
	}

	@Test
	public void runJobTestPost() throws Exception {
		String json = "{\n" + "\"id\":\"3\"\n" 
					+ "\"jobName\":\"Job3emptyfilepath\"\n" 
					+ "\"batchFilePath\":\"E:/Gen/gen.bat\"\n" 
					+ "\"scheduleDate\":\"2018-07-11 11:10:06\"\n" 
					+ "\"status\":\"0\"\n" 
					+ "}";
		mockMvc.perform(MockMvcRequestBuilders.post(IControllerConstants.JOB_SCHEDULER+IControllerConstants.RUN_JOB_SCHEDULER)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				//.andExpect(status().is(400));
				.andExpect(jsonPath("$.statuscode", Matchers.is(404)))
				.andExpect(jsonPath("$.message", Matchers.is("FAILED TO RUN")))
				.andExpect(jsonPath("$.*", Matchers.hasSize(2)));
	//	verify(jobSchedulerDetailService).getJobSchedulerById(new Long(1));

	}

}
