package com.iris.batchJobService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.service.IJobProgressService;

/*
 * JobProgressController class for rest services
 * 
 * @author Rohit Elayathu
 */
@Controller
@RequestMapping("joblist")
public class JobProgressController {

	@Autowired
	private IJobProgressService jobProgressService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * This method gets list of all the executed (completed or failed) jobs
	 */
	@GetMapping("completed")
	public ResponseEntity<List<JobProgressData>> getCompletedJobs() {
		List<JobProgressData> list = jobProgressService.getCompletedJobs();

		logger.info("--Inside getCompletedJobs : list size : " + list.size() + "--");

		return new ResponseEntity<List<JobProgressData>>(list, HttpStatus.OK);
	}

	/*
	 * This method get list of all currently executing jobs
	 */
	@GetMapping("running")
	public ResponseEntity<List<JobProgressData>> getRunningJobs() {
		List<JobProgressData> list = jobProgressService.getRunningJobs();

		logger.info("--Inside getRunningJobs : list size : " + list.size() + "--");

		return new ResponseEntity<List<JobProgressData>>(list, HttpStatus.OK);
	}

}
