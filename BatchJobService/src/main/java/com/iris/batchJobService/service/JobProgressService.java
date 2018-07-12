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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<JobProgressData> getCompletedJobs() {
		List<JobProgressData> list = getJobProgressDAO().getJobsByStatus(JobProgressConstants.COMPLETED);
		list.addAll(getJobProgressDAO().getJobsByStatus(JobProgressConstants.FAILED));

		logger.info("--Inside getCompletedJobs : list size : " + list.size() + "--");

		return list;
	}

	@Override
	public List<JobProgressData> getRunningJobs() {
		List<JobProgressData> list = getJobProgressDAO().getJobsByStatus(JobProgressConstants.RUNNING);

		logger.info("--Inside getRunningJobs : list size : " + list.size() + "--");

		return list;
	}

	public IJobProgressDao getJobProgressDAO() {
		return jobProgressDAO;
	}

	public void setJobProgressDAO(IJobProgressDao jobProgressDAO) {
		this.jobProgressDAO = jobProgressDAO;
	}

}
