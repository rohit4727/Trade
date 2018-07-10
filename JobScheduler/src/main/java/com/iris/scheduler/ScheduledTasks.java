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

@Component
public class ScheduledTasks {

	@Autowired
	CronRepository cronRepository;

	@Scheduled(fixedDelay = 1000)
	public void scheduleJob() {
		List<JobScheduler> joblisttorun = cronRepository.findbycurDate();
		for (JobScheduler job : joblisttorun) {
			try {
				
				String path = "cmd /c start " + job.getBatchFilePath();
				Runtime.getRuntime().exec(path);
			} catch (IOException e) {

			}

		}

	}

}
