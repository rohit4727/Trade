package com.iris.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CompanyController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@RequestMapping("hello")
	String home() {

		// JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		// Job job = (Job) context.getBean("fxMarketPricesETLJob");

		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.toJobParameters();
			JobExecution execution = (JobExecution) jobLauncher.run(job, jobParameters);
			System.out.println("Exit Status : " + execution.getExitStatus());
			System.out.println("Exit Status : " + execution.getAllFailureExceptions());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");
		return "Done";
	}

	@RequestMapping("hi")
	String hi() {
		return "Hi World!";
	}
}
