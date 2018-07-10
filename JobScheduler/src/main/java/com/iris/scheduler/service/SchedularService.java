package com.iris.scheduler.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.repository.CronRepository;

@Service
public class SchedularService implements SchedularServiceImpl {

	@Autowired
	CronRepository cronRepository;

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

}
