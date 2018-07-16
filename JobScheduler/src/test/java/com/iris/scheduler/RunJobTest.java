
package com.iris.scheduler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iris.scheduler.beans.ResponseBean;
import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.rest.controller.ShedulerRestController;
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
	public void runJobSuccessTestPost() throws Exception {
		
		jobScheduler=new JobScheduler("Anchal", "E:/Gen/gen.bat", new Date(), "1");
	
		assertNotNull(jobScheduler);
	}
	
	@Test()
	public void testFilePath() throws ParseException {

		// missing file path
		when(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(0).getBatchFilePath())).thenReturn(false);
		assertFalse("Handle Missing File Path in database",
				schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(0).getBatchFilePath()));
		verify(schedulerService).checkfilepath(SchedularTestUtil.getJobSchedular(0).getBatchFilePath());

		// wrong file path
		when(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(1).getBatchFilePath())).thenReturn(false);
		assertFalse("Handle Wrong File Path in database",
				schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(1).getBatchFilePath()));
		verify(schedulerService).checkfilepath(SchedularTestUtil.getJobSchedular(1).getBatchFilePath());

		// correct file path
		when(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath())).thenReturn(true);
		assertTrue("Handle Correct File Path in database",
				schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath()));
		verify(schedulerService).checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath());
	}

	@Test(expected = NullPointerException.class)
	public void testFilePathWhenNull() throws ParseException {

		when(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath()))
				.thenThrow(NullPointerException.class);

		schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath());
		verify(schedulerService).checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath());
	}

	@Test
	public void runJobSuccessTestPost1() throws Exception {
		
		//when(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(0).getBatchFilePath())).thenReturn(true);
		JobScheduler jobScheduler = jobSchedulerDetailService.getJobSchedulerById(1L);
		
		mockMvc.perform(MockMvcRequestBuilders.post(IControllerConstants.JOB_SCHEDULER + IControllerConstants.RUN_JOB_SCHEDULER)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(jobScheduler)))
				.andExpect(jsonPath("$.statuscode").value(HttpStatus.OK.toString()));
		
		verify(schedulerService).checkfilepath("E:/Gen/gen.bat");
	
	}

	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
