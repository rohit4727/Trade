package com.iris.scheduler;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.service.SchedulerService;

/**
 * @author anchal.handa
 *
 */
/*
 * Scheduler will run after one second and check if there is any job which is
 * pending to run
 * 
 */
@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	SchedulerService schedulerService;

	@Scheduled(fixedDelay = IControllerConstants.SCHEDULE_TIMING)
	public void scheduleJob() {

		List<JobScheduler> joblisttorun = schedulerService.findbycurDate();

		for (JobScheduler job : joblisttorun) {
			try {
				boolean flag = false;
				flag = schedulerService.checkfilepath(job.getBatchFilePath());

				if (flag) {
					schedulerService.runcmd(job.getBatchFilePath());
					job.setStatus(IControllerConstants.DONE);

				} else
					job.setStatus(IControllerConstants.FAIL);
					schedulerService.savestatus(job);
			} catch (Exception ex) {
				logger.info("Failed to run job with id="+job.getJobName() +" and Exception is "+ ex.getMessage());
			}
		}

	}

}