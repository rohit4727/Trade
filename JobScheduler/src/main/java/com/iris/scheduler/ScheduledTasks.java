package com.iris.scheduler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.repository.CronRepository;
import com.iris.scheduler.repository.JobSchedulerRepository;
import com.iris.scheduler.service.SchedularService;
import com.iris.scheduler.service.SchedularServiceImpl;

@Component
public class ScheduledTasks {

	@Autowired
	CronRepository cronRepository;
	@Autowired
	JobSchedulerRepository jobSchedulerRepository;

	@Autowired
	SchedularServiceImpl scheduarService;

	@Scheduled(fixedDelay = IControllerConstants.SCHEDULE_TIMING)
	public void scheduleJob() {
		List<JobScheduler> joblisttorun = cronRepository.findbycurDate();
		for (JobScheduler job : joblisttorun) {
			boolean flag = false;
			flag = scheduarService.checkfilepath(job.getBatchFilePath());

			if (flag) {
				scheduarService.runcmd(job, flag);
				job.setStatus(IControllerConstants.DONE);

			} else
				job.setStatus(IControllerConstants.FAIL);
			jobSchedulerRepository.save(job);
		}

	}

}
