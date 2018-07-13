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

@Service
public class SchedulerServiceImpl implements SchedulerService {

	
	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);
	
	
	@Autowired
	CronRepository cronRepository;
	@Autowired
	JobSchedulerRepository jobSchedulerRepository;
	SchedulerService schedularService;

	

	@Override
	public boolean checkfilepath(String path) {
		
		try {
			File f = new File(path);
			if (f.exists() && !f.isDirectory()) {
				return true;
			}
		}
		catch(Exception e) {
		
			logger.error("Failed to run job with path="+path +" and Exception is "+ e.getMessage());
		}
		return false;

	}

	@Override
	public void runcmd(String batchfilepath) {

		try {

			String cronrun = IControllerConstants.CRON_RUN + batchfilepath;
			Runtime.getRuntime().exec(cronrun);

		} catch (Exception e) {
			logger.error("Failed to run job with batchfilepath="+batchfilepath+" and Exception is "+ e.getMessage());
		}

	}

	@Override
	public void savestatus(JobScheduler job) {

		jobSchedulerRepository.save(job);

	}

	@Override
	public JobScheduler findbyjobId(Long id) {

		return cronRepository.findbyjobId(id);
	}

	@Override
	public List<JobScheduler> findbycurDate() {

		return cronRepository.findbycurDate();
	}

}
