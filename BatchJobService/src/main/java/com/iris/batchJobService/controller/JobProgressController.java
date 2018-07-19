package com.iris.batchJobService.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iris.batchJobService.beans.ResponseBean;
import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.service.IJobProgressService;
import com.iris.batchJobService.util.JobProgressConstants;

/*
 * JobProgressController class for rest services
 * 
 * @author Rohit Elayathu
 */
@RestController
@RequestMapping("joblist")
public class JobProgressController {

	@Autowired
	private IJobProgressService jobProgressService;

	private static final Logger logger = LoggerFactory.getLogger(JobProgressController.class);

	/*
	 * This method gets list of all the executed (completed or failed) jobs
	 */
	@GetMapping("/completed")
	public ResponseEntity<List<JobProgressData>> getCompletedJobs() {
		List<JobProgressData> list = jobProgressService.getCompletedJobs();

		logger.info("Inside getCompletedJobs : list size : " + list.size());

		return new ResponseEntity<List<JobProgressData>>(list, HttpStatus.OK);
	}

	/*
	 * This method gets list of all currently executing jobs
	 */
	@GetMapping("/running")
	public ResponseEntity<List<JobProgressData>> getRunningJobs() {
		List<JobProgressData> list = jobProgressService.getRunningJobs();

		logger.info("Inside getRunningJobs : list size : " + list.size());

		return new ResponseEntity<List<JobProgressData>>(list, HttpStatus.OK);
	}

	/*
	 * This method saves/updated job progress data
	 */
	@PostMapping("/saveJobProgress")
	public ResponseBean SaveJobProgressData(@Valid @RequestBody JobProgressData jobProgressData) {
		JobProgressData jobProgressDataSaved = jobProgressService.saveJobProgress(jobProgressData);
		
		if (jobProgressDataSaved != null && jobProgressDataSaved.getJobId() != 0) {
			return new ResponseBean(HttpStatus.OK.toString(), JobProgressConstants.SUCCESS);
		} else {
			return new ResponseBean(HttpStatus.NOT_FOUND.toString(), JobProgressConstants.FAILED);
		}
	}

}
