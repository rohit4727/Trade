package com.iris.batchJobService.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.batchJobService.dao.IJobProgressDao;
import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.entity.ScheduleJobDetails;
import com.iris.batchJobService.util.JobProgressConstants;

/*
 * Service impl
 * 
 * @author Rohit Elayathu
 */
@Service
public class JobProgressService implements IJobProgressService {

	@Autowired
	private IJobProgressDao jobProgressDao;

	private static final Logger logger = LoggerFactory.getLogger(JobProgressService.class);

	@Override
	public List<ScheduleJobDetails> getCompletedJobs() {
		List<ScheduleJobDetails> list = getJobProgressDao().getJobProgressByStatus(
				Arrays.asList(JobProgressConstants.JOB_COMPLETED, JobProgressConstants.JOB_FAILED));

		logger.info("Inside getCompletedJobs : list size : " + list.size());

		return list;
	}

	@Override
	public List<ScheduleJobDetails> getRunningJobs() {
		List<ScheduleJobDetails> list = getJobProgressDao()
				.getJobProgressByStatus(Arrays.asList(JobProgressConstants.JOB_RUNNING));

		logger.info("Inside getRunningJobs : list size : " + list.size());

		return list;
	}

	@Override
	public JobProgressData saveJobProgress(JobProgressData jobProgressData) {
		JobProgressData jobProgressDataSave = jobProgressDao.saveJobProgress(jobProgressData);
		return jobProgressDataSave;
	}

	public IJobProgressDao getJobProgressDao() {
		return jobProgressDao;
	}

	public void setJobProgressDao(IJobProgressDao jobProgressDao) {
		this.jobProgressDao = jobProgressDao;
	}

}
