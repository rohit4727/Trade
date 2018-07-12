package com.iris.BatchJobService.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.iris.batchJobService.dao.IJobProgressDao;
import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.service.JobProgressService;

public class JobProgressServiceTest {

	JobProgressData jobProgressData = null;

	@Test
	public void testGetCompletedJobs() throws Exception {

		List<JobProgressData> mockList = new ArrayList<JobProgressData>();

		jobProgressData = new JobProgressData();
		jobProgressData.setJobId(1);
		jobProgressData.setStatus(1);
		jobProgressData.setTotalLineCount(1000);
		jobProgressData.setWriterLineCount(1000);

		mockList.add(jobProgressData);

		jobProgressData = new JobProgressData();
		jobProgressData.setJobId(2);
		jobProgressData.setStatus(2);
		jobProgressData.setTotalLineCount(1000);
		jobProgressData.setWriterLineCount(100);

		mockList.add(jobProgressData);

		IJobProgressDao jobProgressDao = mock(IJobProgressDao.class);
		when(jobProgressDao.getJobsByStatus(Mockito.anyInt())).thenReturn(mockList);
		JobProgressService jobProgressService = new JobProgressService();
		jobProgressService.setJobProgressDAO(jobProgressDao);
		List<JobProgressData> result = jobProgressService.getCompletedJobs();

		Assert.assertEquals(mockList, result);

	}
	
	@Test
	public void testGetRunningJobs() throws Exception {

		List<JobProgressData> mockList = new ArrayList<JobProgressData>();

		jobProgressData = new JobProgressData();
		jobProgressData.setJobId(1);
		jobProgressData.setStatus(1);
		jobProgressData.setTotalLineCount(1000);
		jobProgressData.setWriterLineCount(1000);

		mockList.add(jobProgressData);

		jobProgressData = new JobProgressData();
		jobProgressData.setJobId(2);
		jobProgressData.setStatus(2);
		jobProgressData.setTotalLineCount(1000);
		jobProgressData.setWriterLineCount(100);

		mockList.add(jobProgressData);

		IJobProgressDao jobProgressDao = mock(IJobProgressDao.class);
		when(jobProgressDao.getJobsByStatus(Mockito.anyInt())).thenReturn(mockList);
		JobProgressService jobProgressService = new JobProgressService();
		jobProgressService.setJobProgressDAO(jobProgressDao);
		List<JobProgressData> result = jobProgressService.getRunningJobs();

		Assert.assertEquals(mockList, result);

	}

}
