package com.iris.scheduler;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.service.JobSchedulerDetailService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class SchedulerRestControllerTest {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext wac;

	@Autowired
	JobSchedulerDetailService jobSchedulerDetailService;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void verifyCreateJobScheduler() throws Exception {
		JobScheduler jobScheduler = new JobScheduler("Creat Job Test 1", "Batch File Path 1", new Date(), "0");

		mockMvc.perform(post(IControllerConstants.JOB_SCHEDULER + IControllerConstants.CREATE_JOB_SCHEDULER)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(jobScheduler)))
				.andExpect(jsonPath("$.statuscode").value(HttpStatus.OK.toString()));
	}

	@Test
	public void verifyGetAllJobScheduleJobList() throws Exception {
		
		List<JobScheduler>  allScheduledJobDetailList = jobSchedulerDetailService.getAllJobScheduleDetails();

		assertNotNull(allScheduledJobDetailList);
		
		mockMvc.perform(get(IControllerConstants.JOB_SCHEDULER + IControllerConstants.GET_ALL_JOB_SCHEDULE_DETAILS))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$",hasSize(allScheduledJobDetailList.size()) ))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].jobName").value("Creat Job Test 1"));
	}
	
	@Test
	public void verifygetJobSchedulerById() throws Exception {
		
		mockMvc.perform(get(IControllerConstants.JOB_SCHEDULER + "/getJobSchedulerById/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.jobName").value("Creat Job Test 1"));
	}
	
	@Test
	public void verifyUpdateJobSchedulerDetail() throws Exception {
		
		JobScheduler jobScheduler = new JobScheduler("Creat Job Test for Update 1", "Batch File Path 1", new Date(), "0");
		jobScheduler = jobSchedulerDetailService.createOrUpdateJobScheduler(jobScheduler);
		
		assertNotNull(jobScheduler);
		assertNotNull(jobScheduler.getId());
				
		jobScheduler.setJobName("Updated Job Name Test");
		
		mockMvc.perform(put(IControllerConstants.JOB_SCHEDULER + "/updateJobSchedulerDetail/"+jobScheduler.getId())
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(jobScheduler)))
				.andExpect(jsonPath("$.jobName").value("Updated Job Name Test"));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
