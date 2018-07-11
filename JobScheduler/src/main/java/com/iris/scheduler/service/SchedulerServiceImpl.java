package com.iris.scheduler.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.repository.CronRepository;
import com.iris.scheduler.repository.JobSchedulerRepository;

@Service
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	CronRepository cronRepository;
	@Autowired
	JobSchedulerRepository jobSchedulerRepository;

	@Override
	public boolean checkfilepath(String path) {

		File f = new File(path);
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;

	}

	@Override
	public void runcmd(JobScheduler job, boolean flag) {

		try {
			String cronrun = IControllerConstants.CRON_RUN + job.getBatchFilePath();
			Runtime.getRuntime().exec(cronrun);
		} catch (Exception e) {

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
