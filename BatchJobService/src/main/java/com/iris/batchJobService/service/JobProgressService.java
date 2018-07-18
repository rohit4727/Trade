package com.iris.batchJobService.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.repository.JobProgressRepository;
import com.iris.batchJobService.util.JobProgressConstants;

/*
 * Service impl
 * 
 * @author Rohit Elayathu
 */
@Service
public class JobProgressService implements IJobProgressService {

	@Autowired
	private JobProgressRepository jobProgressRepository;

	private static final Logger logger = LoggerFactory.getLogger(JobProgressService.class);

	@Override
	public List<JobProgressData> getCompletedJobs() {
		List<JobProgressData> list = getJobProgressRepository()
				.findByStatusIn(Arrays.asList(JobProgressConstants.JOB_COMPLETED, JobProgressConstants.JOB_FAILED));

		logger.info("Inside getCompletedJobs : list size : " + list.size());

		return list;
	}

	@Override
	public List<JobProgressData> getRunningJobs() {
		List<JobProgressData> list = getJobProgressRepository()
				.findByStatusIn(Arrays.asList(JobProgressConstants.JOB_RUNNING));

		logger.info("Inside getRunningJobs : list size : " + list.size());

		return list;
	}

	public JobProgressRepository getJobProgressRepository() {
		return jobProgressRepository;
	}

	public void setJobProgressRepository(JobProgressRepository jobProgressRepository) {
		this.jobProgressRepository = jobProgressRepository;
	}

}
