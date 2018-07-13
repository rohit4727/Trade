
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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
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

	// Test case when all the information are correct
	@Test
	public void runJobSuccessTestPost() throws Exception {
		String json = "{\"id\" : \"5\"" + ", \"jobName\" : \"Anchal Job Scheduler3\" "
				+ ", \"batchFilePath\" : \"E:/Gen/gen.bat\" " + ", \"scheduleDate\" : \"1499396851000\" "
				+ ", \"status\" : \"0\" " + "}";
		when(schedulerService.checkfilepath("E:/Gen/gen.bat")).thenReturn(true);
		mockMvc.perform(
				MockMvcRequestBuilders.post(IControllerConstants.JOB_SCHEDULER + IControllerConstants.RUN_JOB_SCHEDULER)
						.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(jsonPath("$.statuscode").value("200")).andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.*", Matchers.hasSize(2)));
		verify(schedulerService).checkfilepath("E:/Gen/gen.bat");
		verify(schedulerService).runcmd("E:/Gen/gen.bat");
	}

	// Test case when filepath information is not correct
	@Test
	public void runJobTestWrongFilePathPost() throws Exception {
		String json = "{\"id\" : \"5\"" + ", \"jobName\" : \"Anchal Job Scheduler3\" "
				+ ", \"batchFilePath\" : \"E:/Gen/gen.txt\" " + ", \"scheduleDate\" : \"1499396851000\" "
				+ ", \"status\" : \"0\" " + "}";
		when(schedulerService.checkfilepath("E:/Gen/gen.txt")).thenReturn(false);
		mockMvc.perform(
				MockMvcRequestBuilders.post(IControllerConstants.JOB_SCHEDULER + IControllerConstants.RUN_JOB_SCHEDULER)
						.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(jsonPath("$.statuscode").value("404"))
				.andExpect(jsonPath("$.message").value("FAILED TO RUN"))
				.andExpect(jsonPath("$.*", Matchers.hasSize(2)));
		verify(schedulerService).checkfilepath("E:/Gen/gen.txt");

	}

}
