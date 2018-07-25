package com.iris.BatchJobService.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.iris.batchJobService.dao.IJobProgressDao;
import com.iris.batchJobService.entity.ScheduleJobDetails;
import com.iris.batchJobService.service.JobProgressService;
import com.iris.batchJobService.util.JobStatusEnum;

/*
 * JUnit class for unit testing service
 * 
 * @author Rohit ELayathu
 */
@RunWith(SpringRunner.class)
public class JobProgressServiceTest {

	@MockBean
	private ScheduleJobDetails jobDetails;

	/*
	 * This method does unit testing for getCompletedJobs() service
	 */
	@Test
	public void testGetCompletedJobs() throws Exception {

		List<ScheduleJobDetails> mockList = new ArrayList<ScheduleJobDetails>();
		List<ScheduleJobDetails> expectedList = new ArrayList<ScheduleJobDetails>();

		jobDetails.setId(new Long(1));
		jobDetails.setJobProgressStatus(1);
		jobDetails.setTotalLineCount(1000);
		jobDetails.setWriterLineCount(11000);
		jobDetails.setBatchFilePath("test");
		jobDetails.setStatus("test");
		jobDetails.setScheduleDate(new Date(System.currentTimeMillis()));
		jobDetails.setJobName("Job1");

		mockList.add(jobDetails);
		expectedList.add(jobDetails);

		jobDetails.setId(new Long(2));
		jobDetails.setJobProgressStatus(2);
		jobDetails.setTotalLineCount(1000);
		jobDetails.setWriterLineCount(100);
		jobDetails.setBatchFilePath("test");
		jobDetails.setStatus("test");
		jobDetails.setScheduleDate(new Date(System.currentTimeMillis()));
		jobDetails.setJobName("Job2");

		mockList.add(jobDetails);
		expectedList.add(jobDetails);

		IJobProgressDao jobProgressDao = mock(IJobProgressDao.class);
		when(jobProgressDao.getJobProgressByStatus(
				Arrays.asList(JobStatusEnum.JOB_COMPLETED.getValue(), JobStatusEnum.JOB_FAILED.getValue())))
						.thenReturn(mockList);

		JobProgressService jobProgressService = new JobProgressService();
		jobProgressService.setJobProgressDao(jobProgressDao);
		List<ScheduleJobDetails> result = jobProgressService.getCompletedJobs();

		Assert.assertEquals(expectedList, result);

	}

	/*
	 * This method does unit testing for getRunningJobs() service
	 */
	@Test
	public void testGetRunningJobs() throws Exception {

		List<ScheduleJobDetails> mockList = new ArrayList<ScheduleJobDetails>();
		List<ScheduleJobDetails> expectedList = new ArrayList<ScheduleJobDetails>();

		jobDetails.setId(new Long(1));
		jobDetails.setJobProgressStatus(1);
		jobDetails.setTotalLineCount(1000);
		jobDetails.setWriterLineCount(1000);
		jobDetails.setBatchFilePath("test");
		jobDetails.setStatus("test");
		jobDetails.setScheduleDate(new Date(System.currentTimeMillis()));
		jobDetails.setJobName("Job2");

		mockList.add(jobDetails);
		expectedList.add(jobDetails);

		jobDetails.setId(new Long(2));
		jobDetails.setJobProgressStatus(2);
		jobDetails.setTotalLineCount(1000);
		jobDetails.setWriterLineCount(100);
		jobDetails.setBatchFilePath("test");
		jobDetails.setStatus("test");
		jobDetails.setScheduleDate(new Date(System.currentTimeMillis()));
		jobDetails.setJobName("Job2");

		mockList.add(jobDetails);
		expectedList.add(jobDetails);

		IJobProgressDao jobProgressDao = mock(IJobProgressDao.class);
		when(jobProgressDao.getJobProgressByStatus(Arrays.asList(JobStatusEnum.JOB_RUNNING.getValue())))
				.thenReturn(mockList);

		JobProgressService jobProgressService = new JobProgressService();
		jobProgressService.setJobProgressDao(jobProgressDao);
		List<ScheduleJobDetails> result = jobProgressService.getRunningJobs();

		Assert.assertEquals(expectedList, result);

	}

}
