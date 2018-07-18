package com.iris.BatchJobService.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.repository.JobProgressRepository;
import com.iris.batchJobService.service.JobProgressService;
import com.iris.batchJobService.util.JobProgressConstants;

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

		JobProgressRepository jobProgressRepository = mock(JobProgressRepository.class);
		when(jobProgressRepository
				.findByStatusIn(Arrays.asList(JobProgressConstants.JOB_COMPLETED, JobProgressConstants.JOB_FAILED)))
						.thenReturn(mockList);
		
		JobProgressService jobProgressService = new JobProgressService();
		jobProgressService.setJobProgressRepository(jobProgressRepository);
		List<JobProgressData> result = jobProgressService.getCompletedJobs();

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

		JobProgressRepository jobProgressRepository = mock(JobProgressRepository.class);
		when(jobProgressRepository.findByStatusIn(Arrays.asList(JobProgressConstants.JOB_RUNNING)))
				.thenReturn(mockList);
		
		JobProgressService jobProgressService = new JobProgressService();
		jobProgressService.setJobProgressRepository(jobProgressRepository);
		List<JobProgressData> result = jobProgressService.getRunningJobs();

		Assert.assertEquals(expectedList, result);

	}

}
