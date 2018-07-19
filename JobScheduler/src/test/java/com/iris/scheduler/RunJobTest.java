
package com.iris.scheduler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iris.scheduler.beans.ResponseBean;
import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.dao.JobSchedularDAO;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.repository.CronRepository;
import com.iris.scheduler.rest.controller.ShedulerRestController;
import com.iris.scheduler.runner.ScheduledTasks;
import com.iris.scheduler.service.JobSchedulerDetailService;
import com.iris.scheduler.service.SchedulerService;

/**
 * @author anchal.handa
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class RunJobTest {

	private MockMvc mockMvc;
	@Autowired
	WebApplicationContext wac;
	@InjectMocks
	ShedulerRestController schedulerRestController;

	@Mock
	JobSchedulerDetailService jobSchedulerDetailService;

	@Mock
	SchedulerService schedulerService;

	@Mock
	ScheduledTasks scheduledTasks;
	
	@Mock
	JobSchedularDAO jobSchedularDAO;
	
	@Mock
	CronRepository cronRepository;

	@MockBean
	ResponseBean responseBean;

	@MockBean
	JobScheduler jobScheduler;

	@Before
	public void setUp() throws Exception {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void runJobSuccess() throws Exception {

		JobScheduler jobScheduler = new JobScheduler(SchedularTestUtil.getJobSchedular(4).getJobName(),
				SchedularTestUtil.getJobSchedular(4).getBatchFilePath(), new Date(), "1");
		mockMvc.perform(MockMvcRequestBuilders
				.post(IControllerConstants.JOB_SCHEDULER + IControllerConstants.RUN_JOB_SCHEDULER)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(SchedularTestUtil.asJsonString(jobScheduler)))
				.andExpect(jsonPath("$.statuscode").value(HttpStatus.OK.toString()));
		mockMvc.perform(MockMvcRequestBuilders
				.post(IControllerConstants.JOB_SCHEDULER + IControllerConstants.RUN_JOB_SCHEDULER)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(SchedularTestUtil.asJsonString(jobScheduler)))
				.andExpect(jsonPath("$.statuscode").value(HttpStatus.NOT_FOUND.toString()));

	}

	@Test()
	public void testFilePath() throws ParseException {

		// missing file path
		when(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(0).getBatchFilePath())).thenReturn(false);
		assertFalse(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(0).getBatchFilePath()));
		verify(schedulerService).checkfilepath(SchedularTestUtil.getJobSchedular(0).getBatchFilePath());

		// wrong file path
		when(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(1).getBatchFilePath())).thenReturn(false);
		assertFalse(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(1).getBatchFilePath()));
		verify(schedulerService).checkfilepath(SchedularTestUtil.getJobSchedular(1).getBatchFilePath());

		// correct file path
		when(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath())).thenReturn(true);
		assertTrue(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath()));
		verify(schedulerService).checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath());
	}

	@Test
	public void runJobFailure() throws Exception {

		JobScheduler jobScheduler = new JobScheduler(SchedularTestUtil.getJobSchedular(1).getJobName(),
				SchedularTestUtil.getJobSchedular(1).getBatchFilePath(), new Date(), "1");

		mockMvc.perform(MockMvcRequestBuilders
				.post(IControllerConstants.JOB_SCHEDULER + IControllerConstants.RUN_JOB_SCHEDULER)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(SchedularTestUtil.asJsonString(jobScheduler)))
				.andExpect(jsonPath("$.statuscode").value(HttpStatus.NOT_FOUND.toString()));

	}


}
