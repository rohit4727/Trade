package com.iris.scheduler.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iris.scheduler.beans.ResponseBean;
import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.service.JobSchedulerDetailService;
import com.iris.scheduler.service.SchedulerService;

/**
 * 
 * @author pushpendra.singh
 *
 */
@RestController
@RequestMapping(IControllerConstants.JOB_SCHEDULER)
public class ShedulerRestController {

	private static final Logger logger = LoggerFactory.getLogger(ShedulerRestController.class);

	@Autowired
	SchedulerService schedularService;
	@Autowired
	JobSchedulerDetailService jobSchedulerDetailService;

	@GetMapping(IControllerConstants.GET_ALL_JOB_SCHEDULE_DETAILS)
	public List<JobScheduler> getAllJobScheduleDetails() {
		return jobSchedulerDetailService.getAllJobScheduleDetails();
	}

	/**
	 * This controller will create job schedule details and return success for fail
	 * response
	 * 
	 * @param jobScheduler
	 * @return ResponseBean
	 */
	@PostMapping(IControllerConstants.CREATE_JOB_SCHEDULER)
	public ResponseBean createJobScheduler(@Valid @RequestBody JobScheduler jobScheduler) {
		try {
			jobScheduler = jobSchedulerDetailService.createOrUpdateJobScheduler(jobScheduler);

		} catch (Exception ex) {
			logger.info("createJobScheduler : create / schedule job faied for JobName: " + jobScheduler.getJobName(),
					ex);
		}
		if (jobScheduler != null && jobScheduler.getId() != null) {
			return new ResponseBean(HttpStatus.OK.toString(), IControllerConstants.SUCCESS);
		} else {
			return new ResponseBean(HttpStatus.NOT_FOUND.toString(), IControllerConstants.FAILED);
		}
	}

	/**
	 * This controller will Get a Single JobScheduler
	 * 
	 * @param jobId
	 * @return JobScheduler
	 */
	@GetMapping(IControllerConstants.GET_JOB_SCHEDULER_BY_ID + IControllerConstants.ID_PARAM)
	public JobScheduler getJobSchedulerById(@PathVariable(value = IControllerConstants.ID) Long jobId) {
		return jobSchedulerDetailService.getJobSchedulerById(jobId);

	}

	/**
	 * This controller will Update a JobScheduler
	 * 
	 * @param jobId
	 * @param jobSchedulerDetails
	 * @return
	 */
	@PostMapping(IControllerConstants.UPDATE_JOB_SCHEDULER_DETAIL + IControllerConstants.ID_PARAM)
	public ResponseBean updateJobSchedulerDetail(@PathVariable(value = IControllerConstants.ID) Long jobId,
			@RequestBody JobScheduler jobSchedulerDetails) {

		JobScheduler jobScheduler = jobSchedulerDetailService.getJobSchedulerById(jobId);

		if (jobSchedulerDetails.getJobName() != null) {
			jobScheduler.setJobName(jobSchedulerDetails.getJobName());
		}

		if (jobSchedulerDetails.getBatchFilePath() != null) {
			jobScheduler.setBatchFilePath(jobSchedulerDetails.getBatchFilePath());
		}

		if (jobSchedulerDetails.getScheduleDate() != null) {
			jobScheduler.setScheduleDate(jobSchedulerDetails.getScheduleDate());
		}

		JobScheduler updatedJobScheduler = null;
		try {
			updatedJobScheduler = jobSchedulerDetailService.createOrUpdateJobScheduler(jobScheduler);

		} catch (Exception ex) {
			logger.info("updateJobSchedulerDetail : update schedule job faied for jobId: " + jobId, ex);
		}

		if (updatedJobScheduler != null && updatedJobScheduler.getId() != null) {
			return new ResponseBean(HttpStatus.OK.toString(), IControllerConstants.SUCCESS);
		} else {
			return new ResponseBean(HttpStatus.NOT_FOUND.toString(), IControllerConstants.FAILED);
		}

	}

	/**
	 * This controller will Delete a JobScheduler
	 * 
	 * @param jobId
	 * @return ResponseEntity<?>
	 */
	@DeleteMapping(IControllerConstants.DELETE_JOB_SCHEDULER + IControllerConstants.ID_PARAM)
	public ResponseEntity<?> deleteJobScheduler(@PathVariable(value = IControllerConstants.ID) Long jobId) {
		JobScheduler jobScheduler = jobSchedulerDetailService.getJobSchedulerById(jobId);

		jobSchedulerDetailService.deleteJobScheduler(jobScheduler);

		return ResponseEntity.ok().build();

	}

	/**
	 * @author anchal.handa
	/**
	 * This controller will run job and insert the details in database for future
	 * reference, Return SUCCESS response in case of successfully runs of jobs and
	 * FAILED TO RUN in case of FAILURE due to any reason
	 * 
	 * @param jobScheduler
	 * @return ResponseBean
	 */
	@PostMapping(IControllerConstants.RUN_JOB_SCHEDULER)
	@ResponseBody
	public ResponseBean runJob(@Valid @RequestBody JobScheduler jobScheduler) {

		boolean flag = false;
		if (jobScheduler != null) {
			try {
				jobScheduler = jobSchedulerDetailService.createOrUpdateJobScheduler(jobScheduler);
				flag = schedularService.checkfilepath(jobScheduler.getBatchFilePath());
				if (flag) {
					schedularService.runcmd(jobScheduler.getBatchFilePath(), jobScheduler.getId());
					return new ResponseBean(HttpStatus.OK.toString(), IControllerConstants.SUCCESS);
				} else {
					jobScheduler.setStatus(IControllerConstants.FAIL);
					jobScheduler = jobSchedulerDetailService.createOrUpdateJobScheduler(jobScheduler);
					return new ResponseBean(HttpStatus.NOT_FOUND.toString(), IControllerConstants.FAILED);
				}
			} catch (Exception ex) {
				logger.info("CreateJobScheduler : Create Job Failed for Run Job Having Name : ",
						jobScheduler.getJobName()+" and Exception is"+ex.getMessage());
			}

		}

		return new ResponseBean(HttpStatus.NOT_FOUND.toString(), IControllerConstants.FAILED);

	}
}
