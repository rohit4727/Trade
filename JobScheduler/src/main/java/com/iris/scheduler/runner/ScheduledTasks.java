package com.iris.scheduler.runner;

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
 * This method will run after one second (check and runs if there is any job
 * which is pending to run)
 * 
 */
@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	private SchedulerService schedulerService;

	@Scheduled(fixedDelay = IControllerConstants.SCHEDULE_TIMING)
	public boolean scheduleJob() {

		List<JobScheduler> joblisttorun = schedulerService.findbycurDate();
		try {
			boolean flag =false;
		for (JobScheduler job : joblisttorun) {
			try {
				flag = false;
				flag = schedulerService.checkfilepath(job.getBatchFilePath());

				if (flag) {
					schedulerService.runcmd(job.getBatchFilePath(), job.getId());
					job.setStatus(IControllerConstants.DONE);

				} else
					job.setStatus(IControllerConstants.FAIL);
					schedulerService.savestatus(job);
			} catch (Exception ex) {
				logger.error(IControllerConstants.SCHEDULEDJOBERROR , job.getJobName() ,  ex);
			}
		}
		}
		catch(Exception e) {
			logger.error(IControllerConstants.GETSCHEDULEDJOBERROR , e);
		}
		return true;
		
	}

}