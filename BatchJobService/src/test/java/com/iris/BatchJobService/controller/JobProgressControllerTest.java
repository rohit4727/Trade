package com.iris.BatchJobService.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iris.batchJobService.controller.JobProgressController;
import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.entity.ScheduleJobDetails;
import com.iris.batchJobService.service.IJobProgressService;

/*
 * JUnit class for unit testing controller
 * 
 * @author Rohit ELayathu
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = JobProgressController.class, secure = false)
public class JobProgressControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IJobProgressService jobProgressService;

	private ScheduleJobDetails jobDetails = null;

	/*
	 * This method does unit testing for '/joblist/completed' web service
	 */
	@Test
	public void testGetCompletedJobs() throws Exception {

		List<ScheduleJobDetails> mockList = new ArrayList<ScheduleJobDetails>();

		jobDetails = new ScheduleJobDetails();
		jobDetails.setId(new Long(1));
		jobDetails.setJobProgressStatus(1);
		jobDetails.setTotalLineCount(1000);
		jobDetails.setWriterLineCount(1000);
		jobDetails.setBatchFilePath("test");
		jobDetails.setStatus("test");
		jobDetails.setScheduleDate(Date.valueOf("2018-07-20"));
		jobDetails.setJobName("Job1");

		mockList.add(jobDetails);

		jobDetails = new ScheduleJobDetails();
		jobDetails.setId(new Long(2));
		jobDetails.setJobProgressStatus(2);
		jobDetails.setTotalLineCount(1000);
		jobDetails.setWriterLineCount(100);
		jobDetails.setBatchFilePath("test");
		jobDetails.setStatus("test");
		jobDetails.setScheduleDate(Date.valueOf("2018-07-20"));
		jobDetails.setJobName("Job2");

		mockList.add(jobDetails);

		Mockito.when(jobProgressService.getCompletedJobs()).thenReturn(mockList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/joblist/completed")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{id:1,totalLineCount:1000,writerLineCount:1000,jobProgressStatus:1,batchFilePath:test,status:test,scheduleDate:2018-07-20,jobName:Job1},"
				+ "{id:2,totalLineCount:1000,writerLineCount:100,jobProgressStatus:2,batchFilePath:test,status:test,scheduleDate:2018-07-20,jobName:Job2}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);

	}

	/*
	 * This method does unit testing for '/joblist/running' web service
	 */
	@Test
	public void testGetRunningJobs() throws Exception {

		List<ScheduleJobDetails> mockList = new ArrayList<ScheduleJobDetails>();

		jobDetails = new ScheduleJobDetails();
		jobDetails.setId(new Long(1));
		jobDetails.setJobProgressStatus(0);
		jobDetails.setTotalLineCount(1000);
		jobDetails.setWriterLineCount(100);
		jobDetails.setBatchFilePath("test");
		jobDetails.setStatus("test");
		jobDetails.setScheduleDate(Date.valueOf("2018-07-20"));
		jobDetails.setJobName("Job1");

		mockList.add(jobDetails);

		jobDetails = new ScheduleJobDetails();
		jobDetails.setId(new Long(2));
		jobDetails.setJobProgressStatus(0);
		jobDetails.setTotalLineCount(1000);
		jobDetails.setWriterLineCount(90);
		jobDetails.setBatchFilePath("test");
		jobDetails.setStatus("test");
		jobDetails.setScheduleDate(Date.valueOf("2018-07-20"));
		jobDetails.setJobName("Job2");

		mockList.add(jobDetails);

		Mockito.when(jobProgressService.getRunningJobs()).thenReturn(mockList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/joblist/running")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{id:1,totalLineCount:1000,writerLineCount:100,jobProgressStatus:0,batchFilePath:test,status:test,scheduleDate:2018-07-20,jobName:Job1},"
				+ "{id:2,totalLineCount:1000,writerLineCount:90,jobProgressStatus:0,batchFilePath:test,status:test,scheduleDate:2018-07-20,jobName:Job2}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}

	/*
	 * This method does unit testing for '/joblist/saveJobProgress' web service
	 */
	@Test
	public void testSaveJobProgressData() throws Exception {

		JobProgressData jobProgressData = new JobProgressData();
		jobProgressData.setJobId(new Long(1));

		Mockito.when(jobProgressService.saveJobProgress(Mockito.any(JobProgressData.class)))
				.thenReturn(jobProgressData);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/joblist/saveJobProgress")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(jobProgressData));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		;

		String expected = "{statuscode:'200',message:SUCCESS}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
