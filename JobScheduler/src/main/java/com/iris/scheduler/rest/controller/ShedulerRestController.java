package com.iris.scheduler.rest.controller;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.iris.scheduler.exception.ResourceNotFoundException;
import com.iris.scheduler.repository.CronRepository;
import com.iris.scheduler.service.JobSchedulerDetailService;
import com.iris.scheduler.service.SchedularServiceImpl;

@RestController
@RequestMapping(IControllerConstants.JOB_SCHEDULER)
public class ShedulerRestController {

	@Autowired
	SchedularServiceImpl schedularService;
	@Autowired
	JobSchedulerDetailService jobSchedulerDetailService;

	@Autowired
	CronRepository cronRepository;

	@GetMapping(IControllerConstants.GET_ALL_JOB_SCHEDULE_DETAILS)
	public List<JobScheduler> getAllJobScheduleDetails() {
		return jobSchedulerDetailService.getAllJobScheduleDetails();
	}

	@PostMapping(IControllerConstants.CREATE_JOB_SCHEDULER)
	public JobScheduler createJobScheduler(@Valid @RequestBody JobScheduler jobScheduler) {
		return jobSchedulerDetailService.createOrUpdateJobScheduler(jobScheduler);
	}

	// Get a Single JobScheduler
	@GetMapping(IControllerConstants.GET_JOB_SCHEDULER_BY_ID)
	public JobScheduler getJobSchedulerById(@PathVariable(value = IControllerConstants.ID) Long jobId) {
		return jobSchedulerDetailService.getJobSchedulerById(jobId);
				
	}

	// Update a JobScheduler
	@PutMapping(IControllerConstants.UPDATE_JOB_SCHEDULER_DETAIL)
	public JobScheduler updateJobSchedulerDetail(@PathVariable(value = IControllerConstants.ID) Long jobId,
			@Valid @RequestBody JobScheduler jobSchedulerDetails) {

		JobScheduler jobScheduler = jobSchedulerDetailService.getJobSchedulerById(jobId);

		jobScheduler.setJobName(jobSchedulerDetails.getJobName());
		jobScheduler.setBatchFilePath(jobSchedulerDetails.getBatchFilePath());
		jobScheduler.setScheduleDate(jobSchedulerDetails.getScheduleDate());

		JobScheduler updatedJobScheduler = jobSchedulerDetailService.createOrUpdateJobScheduler(jobSchedulerDetails);
		return updatedJobScheduler;
	}

	// Delete a JobScheduler
	@DeleteMapping(IControllerConstants.DELETE_JOB_SCHEDULER)
	public ResponseEntity<?> deleteJobScheduler(@PathVariable(value = IControllerConstants.ID) Long jobId) {
		JobScheduler jobScheduler = jobSchedulerDetailService.getJobSchedulerById(jobId);

		jobSchedulerDetailService.deleteJobScheduler(jobScheduler);

		return ResponseEntity.ok().build();
	}

	
	@GetMapping(IControllerConstants.RUN_JOB_SCHEDULER)
	@ResponseBody
	public ResponseBean runJob(@PathVariable Long id) {
		boolean flag = false;
		JobScheduler job = cronRepository.findbyjobId(id);
		flag = schedularService.checkfilepath(job.getBatchFilePath());

		if (flag) {
			schedularService.runcmd(job, flag);
			job.setStatus(IControllerConstants.DONE);
			jobSchedulerDetailService.createOrUpdateJobScheduler(job);
			return new ResponseBean(IControllerConstants.DONE, IControllerConstants.SUCCESS);
		} else {
			job.setStatus(IControllerConstants.FAIL);
			jobSchedulerDetailService.createOrUpdateJobScheduler(job);
			return new ResponseBean(IControllerConstants.FAIL, IControllerConstants.FAILED);
		}

	}

}
