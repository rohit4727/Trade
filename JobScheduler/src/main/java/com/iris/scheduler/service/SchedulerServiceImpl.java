package com.iris.scheduler.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.scheduler.ScheduledTasks;
import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.repository.CronRepository;
import com.iris.scheduler.repository.JobSchedulerRepository;

/**
 * @author anchal.handa
 *
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	@Autowired
	CronRepository cronRepository;
	@Autowired
	JobSchedulerRepository jobSchedulerRepository;
	SchedulerService schedularService;

	/*
	 * Check the file path/Job script path if exist
	 */ @Override
	public boolean checkfilepath(String path) {

		try {
			File f = new File(path);
			if (f.exists() && !f.isDirectory()) {
				return true;
			}
		} catch (Exception e) {

			logger.error("Failed To Run Job With Path=" + path + " and Exception is " + e.getMessage());
		}
		return false;

	}

	/*
	 * Runs the batch file of different tasks/scripts with job id and job script
	 * path
	 */
	@Override
	public void runcmd(String batchfilepath, Long id) {

		try {

			String cronrun = IControllerConstants.CRON_RUN + batchfilepath + " " + id;
			Runtime.getRuntime().exec(cronrun);

		} catch (Exception e) {
			logger.error(
					"Failed To Run Job With Batchfilepath=" + batchfilepath + " and Exception is " + e.getMessage());
		}

	}

	/*
	 * To Save the status of Job in Database 1 means Run successfully 2 means
	 * Failure and 0 means to be process as per scheduler time
	 */
	@Override
	public void savestatus(JobScheduler job) {

		jobSchedulerRepository.save(job);

	}

	/*
	 * Find the Jobs by Id
	 * 
	 */
	@Override
	public JobScheduler findbyjobId(Long id) {

		return cronRepository.findbyjobId(id);
	}

	/*
	 * Find the Jobs by CURRENT DATETIME
	 * 
	 */
	@Override
	public List<JobScheduler> findbycurDate() {

		return cronRepository.findbycurDate();
	}

}
