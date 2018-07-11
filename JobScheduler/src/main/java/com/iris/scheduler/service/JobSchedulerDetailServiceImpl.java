package com.iris.scheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.exception.ResourceNotFoundException;
import com.iris.scheduler.repository.JobSchedulerRepository;

@Service
public class JobSchedulerDetailServiceImpl implements JobSchedulerDetailService {
	
	@Autowired
	JobSchedulerRepository jobSchedulerRepository;

	@Override
	public List<JobScheduler> getAllJobScheduleDetails() {
		
		return jobSchedulerRepository.findAll();
	}

	@Override
	public JobScheduler createOrUpdateJobScheduler(JobScheduler jobScheduler) {
		
		return jobSchedulerRepository.save(jobScheduler);
	}

	@Override
	public JobScheduler getJobSchedulerById(Long jobId) {
		return jobSchedulerRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException(IControllerConstants.JOB_SCHEDULER_LBL,
						IControllerConstants.ID, jobId));
    }

	@Override
	public void deleteJobScheduler(JobScheduler jobScheduler) {
		jobSchedulerRepository.delete(jobScheduler);
	}

}
