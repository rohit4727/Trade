package com.iris.BatchJobService.controller;

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
import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.service.IJobProgressService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = JobProgressController.class, secure = false)
public class JobProgressControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IJobProgressService jobProgressService;
	
	JobProgressData jobProgressData = null;

	@Test
	public void testGetCompletedJobs() throws Exception {

		List<JobProgressData> mockList = new ArrayList<JobProgressData>();

		jobProgressData = new JobProgressData();
		jobProgressData.setJobId(new Long(1));
		jobProgressData.setStatus(1);
		jobProgressData.setTotalLineCount(1000);
		jobProgressData.setWriterLineCount(1000);

		mockList.add(jobProgressData);
		
		jobProgressData = new JobProgressData();
		jobProgressData.setJobId(new Long(2));
		jobProgressData.setStatus(2);
		jobProgressData.setTotalLineCount(1000);
		jobProgressData.setWriterLineCount(100);

		mockList.add(jobProgressData);

		Mockito.when(jobProgressService.getCompletedJobs()).thenReturn(mockList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/joblist/completed")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{jobId:1,totalLineCount:1000,writerLineCount:1000,status:1},"
				+ "{jobId:2,totalLineCount:1000,writerLineCount:100,status:2}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);

	}
	
	@Test
	public void testGetRunningJobs() throws Exception {

		List<JobProgressData> mockList = new ArrayList<JobProgressData>();

		jobProgressData = new JobProgressData();
		jobProgressData.setJobId(new Long(1));
		jobProgressData.setStatus(0);
		jobProgressData.setTotalLineCount(1000);
		jobProgressData.setWriterLineCount(100);

		mockList.add(jobProgressData);
		
		jobProgressData = new JobProgressData();
		jobProgressData.setJobId(new Long(2));
		jobProgressData.setStatus(0);
		jobProgressData.setTotalLineCount(1000);
		jobProgressData.setWriterLineCount(90);

		mockList.add(jobProgressData);

		Mockito.when(jobProgressService.getRunningJobs()).thenReturn(mockList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/joblist/running")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{jobId:1,totalLineCount:1000,writerLineCount:100,status:0},"
				+ "{jobId:2,totalLineCount:1000,writerLineCount:90,status:0}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);

	}

}
