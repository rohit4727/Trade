package com.iris.BatchJobService.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.iris.batchJobService.dao.IJobProgressDao;
import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.service.JobProgressService;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class JobProgressServiceTest {

	@MockBean
	JobProgressData jobProgressData;

	@Test
	public void testGetCompletedJobs() throws Exception {

		List<JobProgressData> mockList = new ArrayList<JobProgressData>();
		List<JobProgressData> expectedList = new ArrayList<JobProgressData>();

		jobProgressData.setJobId(1);
		jobProgressData.setStatus(1);
		jobProgressData.setTotalLineCount(1000);
		jobProgressData.setWriterLineCount(11000);

		mockList.add(jobProgressData);
		expectedList.add(jobProgressData);

		jobProgressData.setJobId(2);
		jobProgressData.setStatus(2);
		jobProgressData.setTotalLineCount(1000);
		jobProgressData.setWriterLineCount(100);

		mockList.add(jobProgressData);
		expectedList.add(jobProgressData);

		IJobProgressDao jobProgressDao = mock(IJobProgressDao.class);
		when(jobProgressDao.getJobsByStatus(Mockito.anyInt())).thenReturn(mockList);
		JobProgressService jobProgressService = new JobProgressService();
		jobProgressService.setJobProgressDAO(jobProgressDao);
		List<JobProgressData> result = jobProgressService.getCompletedJobs();
		
		expectedList.add(expectedList.get(0));
		expectedList.add(expectedList.get(1));

		Assert.assertEquals(expectedList, result);

	}
	
	@Test
	public void testGetRunningJobs() throws Exception {

		List<JobProgressData> mockList = new ArrayList<JobProgressData>();
		List<JobProgressData> expectedList = new ArrayList<JobProgressData>();

		jobProgressData.setJobId(1);
		jobProgressData.setStatus(1);
		jobProgressData.setTotalLineCount(1000);
		jobProgressData.setWriterLineCount(1000);

		mockList.add(jobProgressData);
		expectedList.add(jobProgressData);

		jobProgressData.setJobId(2);
		jobProgressData.setStatus(2);
		jobProgressData.setTotalLineCount(1000);
		jobProgressData.setWriterLineCount(100);

		mockList.add(jobProgressData);
		expectedList.add(jobProgressData);

		IJobProgressDao jobProgressDao = mock(IJobProgressDao.class);
		when(jobProgressDao.getJobsByStatus(Mockito.anyInt())).thenReturn(mockList);
		JobProgressService jobProgressService = new JobProgressService();
		jobProgressService.setJobProgressDAO(jobProgressDao);
		List<JobProgressData> result = jobProgressService.getRunningJobs();

		Assert.assertEquals(expectedList, result);

	}

}
