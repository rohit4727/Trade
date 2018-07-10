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
import com.iris.scheduler.repository.JobSchedulerRepository;
<<<<<<< .mine

=======
import com.iris.scheduler.service.SchedularServiceImpl;
>>>>>>> .theirs

import com.iris.scheduler.service.SchedulerService;

@RestController
@RequestMapping(IControllerConstants.JOB_SCHEDULER)
public class ShedulerRestController {

	@Autowired
	JobSchedulerRepository jobSchedulerRepository;

	@Autowired
	SchedulerService schedularService;


	@GetMapping(IControllerConstants.GET_ALL_JOB_SCHEDULE_DETAILS)
	public List<JobScheduler> getAllJobScheduleDetails() {
		return jobSchedulerRepository.findAll();
	}

	@PostMapping(IControllerConstants.CREATE_JOB_SCHEDULER)
	public JobScheduler createJobScheduler(@Valid @RequestBody JobScheduler jobScheduler) {
		return jobSchedulerRepository.save(jobScheduler);
	}

	// Get a Single JobScheduler
	@GetMapping(IControllerConstants.GET_JOB_SCHEDULER_BY_ID)
	public JobScheduler getJobSchedulerById(@PathVariable(value = IControllerConstants.ID) Long jobId) {
		return jobSchedulerRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException(IControllerConstants.JOB_SCHEDULER_LBL,
						IControllerConstants.ID, jobId));
	}

	// Update a JobScheduler
	@PutMapping(IControllerConstants.UPDATE_JOB_SCHEDULER_DETAIL)
	public JobScheduler updateJobSchedulerDetail(@PathVariable(value = IControllerConstants.ID) Long jobId,
			@Valid @RequestBody JobScheduler jobSchedulerDetails) {

		JobScheduler jobScheduler = jobSchedulerRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException(IControllerConstants.JOB_SCHEDULER_LBL,
						IControllerConstants.ID, jobId));

		jobScheduler.setJobName(jobSchedulerDetails.getJobName());
		jobScheduler.setBatchFilePath(jobSchedulerDetails.getBatchFilePath());
		jobScheduler.setScheduleDate(jobSchedulerDetails.getScheduleDate());

		JobScheduler updatedJobScheduler = jobSchedulerRepository.save(jobSchedulerDetails);
		return updatedJobScheduler;
	}

	// Delete a JobScheduler
	@DeleteMapping(IControllerConstants.DELETE_JOB_SCHEDULER)
	public ResponseEntity<?> deleteJobScheduler(@PathVariable(value = IControllerConstants.ID) Long jobId) {
		JobScheduler jobScheduler = jobSchedulerRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException(IControllerConstants.JOB_SCHEDULER_LBL,
						IControllerConstants.ID, jobId));

		jobSchedulerRepository.delete(jobScheduler);

		return ResponseEntity.ok().build();
	}

	// Get JobScheduler By Schedule Date
	@GetMapping(IControllerConstants.FIND_JOB_SCHEDULER_BY_SCHEDULE_DATE)
	public List<JobScheduler> findJobSchedulerByScheduleDate(
			@PathVariable(value = IControllerConstants.SCHEDULE_DATE) Date scheduleDate) {
		return jobSchedulerRepository.findByScheduleDate(scheduleDate);
	}

	@GetMapping(IControllerConstants.RUN_JOB_SCHEDULER)
	@ResponseBody
	public ResponseBean runJob(@PathVariable Long id) {
		boolean flag = false;
		JobScheduler job = schedularService.findbyjobId(id);
		flag = schedularService.checkfilepath(job.getBatchFilePath());

		if (flag) {
			schedularService.runcmd(job, flag);
			job.setStatus(IControllerConstants.DONE);
			schedularService.savestatus(job);
			return new ResponseBean(IControllerConstants.DONE, IControllerConstants.SUCCESS);
		} else {
			job.setStatus(IControllerConstants.FAIL);
			schedularService.savestatus(job);
			return new ResponseBean(IControllerConstants.FAIL, IControllerConstants.FAILED);
		}

	}

}
