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

import com.iris.batchJobService.controller.JobProgressController;
import com.iris.batchJobService.entity.ScheduleJobDetails;
import com.iris.batchJobService.service.IJobProgressService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = JobProgressController.class, secure = false)
public class JobProgressControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IJobProgressService jobProgressService;
	
	ScheduleJobDetails jobDetails = null;

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
		jobDetails.setScheduleDate(new Date(System.currentTimeMillis()));
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

		System.out.println(result.getResponse());
		String expected = "[{id:1,totalLineCount:1000,writerLineCount:1000,jobProgressStatus:1,batchFilePath:test,status:test,scheduleDate:2018-07-20,jobName:Job1},"
				+ "{id:2,totalLineCount:1000,writerLineCount:100,jobProgressStatus:2,batchFilePath:test,status:test,scheduleDate:2018-07-20,jobName:Job2}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);

	}
	
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
		jobDetails.setScheduleDate(new Date(System.currentTimeMillis()));
		jobDetails.setJobName("Job1");

		mockList.add(jobDetails);
		
		jobDetails = new ScheduleJobDetails();
		jobDetails.setId(new Long(2));
		jobDetails.setJobProgressStatus(0);
		jobDetails.setTotalLineCount(1000);
		jobDetails.setWriterLineCount(90);
		jobDetails.setBatchFilePath("test");
		jobDetails.setStatus("test");
		jobDetails.setScheduleDate(new Date(System.currentTimeMillis()));
		jobDetails.setJobName("Job2");

		mockList.add(jobDetails);

		Mockito.when(jobProgressService.getRunningJobs()).thenReturn(mockList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/joblist/running")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{id:1,totalLineCount:1000,writerLineCount:100,jobProgressStatus:0,batchFilePath:test,status:test,scheduleDate:2018-07-20,jobName:Job1},"
				+ "{id:2,totalLineCount:1000,writerLineCount:90,jobProgressStatus:0,batchFilePath:test,status:test,scheduleDate:2018-07-20,jobName:Job2}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);

	}

}
