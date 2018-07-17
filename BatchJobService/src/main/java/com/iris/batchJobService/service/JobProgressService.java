package com.iris.batchJobService.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.batchJobService.dao.IJobProgressDao;
import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.util.JobProgressConstants;

/*
 * Service impl
 * 
 * @author Rohit Elayathu
 */
@Service
public class JobProgressService implements IJobProgressService {

	@Autowired
	private IJobProgressDao jobProgressDAO;

	private static final Logger logger = LoggerFactory.getLogger(JobProgressService.class);

	@Override
	public List<JobProgressData> getCompletedJobs() {
		List<JobProgressData> list = getJobProgressDAO().getJobsByStatus(JobProgressConstants.JOB_COMPLETED);
		list.addAll(getJobProgressDAO().getJobsByStatus(JobProgressConstants.JOB_FAILED));

		logger.info("Inside getCompletedJobs : list size : " + list.size());

		return list;
	}

	@Override
	public List<JobProgressData> getRunningJobs() {
		List<JobProgressData> list = getJobProgressDAO().getJobsByStatus(JobProgressConstants.JOB_RUNNING);

		logger.info("Inside getRunningJobs : list size : " + list.size());

		return list;
	}

	public IJobProgressDao getJobProgressDAO() {
		return jobProgressDAO;
	}

	public void setJobProgressDAO(IJobProgressDao jobProgressDAO) {
		this.jobProgressDAO = jobProgressDAO;
	}

}
