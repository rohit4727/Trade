package com.iris.mvc.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.batch.util.LogMsg;
import com.iris.mvc.service.SpringBatchService;

/**
 * Service Layer Implementaion: SpringBatchServiceImpl
 *
 * @author Saurabh Gupta
 */
@Service
public class SpringBatchServiceImpl implements SpringBatchService {

	private static final Logger log = LoggerFactory.getLogger(SpringBatchServiceImpl.class);

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public boolean runJob(Long jobId) {
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("jobId", jobId).addDate("date", new Date()).toJobParameters();
			JobExecution execution = (JobExecution) jobLauncher.run(job, jobParameters);
			log.debug(LogMsg.EXIT_STATUS + execution.getExitStatus());
			log.debug(LogMsg.EXIT_STATUS + execution.getAllFailureExceptions());
			return execution.getExitStatus().equals(ExitStatus.COMPLETED);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
}
