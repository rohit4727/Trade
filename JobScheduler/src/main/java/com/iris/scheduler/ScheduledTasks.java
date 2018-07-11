package com.iris.scheduler;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.service.SchedulerService;


@Component
public class ScheduledTasks {

	@Autowired
	SchedulerService scheduarService;

	//@Scheduled(fixedDelay = IControllerConstants.SCHEDULE_TIMING)
	public void scheduleJob() {
		List<JobScheduler> joblisttorun = scheduarService.findbycurDate();
		for (JobScheduler job : joblisttorun) {
			boolean flag = false;
			flag = scheduarService.checkfilepath(job.getBatchFilePath());

			if (flag) {
				scheduarService.runcmd(job, flag);
				job.setStatus(IControllerConstants.DONE);

			} else
				job.setStatus(IControllerConstants.FAIL);
			scheduarService.savestatus(job);
		}

	}

}
