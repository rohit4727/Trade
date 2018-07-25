package com.iris.scheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.exception.ResourceNotFoundException;
import com.iris.scheduler.repository.JobSchedulerRepository;

/**
 * 
 * @author pushpendra.singh
 *
 */
@Service
public class JobSchedulerDetailServiceImpl implements JobSchedulerDetailService {
	
	@Autowired
	private JobSchedulerRepository jobSchedulerRepository;

	/**
	 * This method will get all sheduled job list
	 * @return List<JobScheduler>
	 */
	@Override
	public List<JobScheduler> getAllJobScheduleDetails() {
		
		return jobSchedulerRepository.findAll();
	}

	/**
	 * This will create or update job schedule details
	 * @param jobScheduler
	 * @return JobScheduler
	 */
	@Override
	public JobScheduler createOrUpdateJobScheduler(JobScheduler jobScheduler) {
		
		return jobSchedulerRepository.save(jobScheduler);
	}
	
	/**
	 * This will update job schedule details
	 * @param jobScheduler
	 * @return JobScheduler
	 */
	@Override
	public JobScheduler updateJobScheduler(JobScheduler jobScheduler, JobScheduler jobSchedulerDetails) {
		
		if (jobSchedulerDetails.getJobName() != null) {
			jobScheduler.setJobName(jobSchedulerDetails.getJobName());
		}

		if (jobSchedulerDetails.getBatchFilePath() != null) {
			jobScheduler.setBatchFilePath(jobSchedulerDetails.getBatchFilePath());
		}

		if (jobSchedulerDetails.getScheduleDate() != null) {
			jobScheduler.setScheduleDate(jobSchedulerDetails.getScheduleDate());
		}
		return jobSchedulerRepository.save(jobScheduler);
	}

	/**
	 * This method will get schedule job details by Id
	 * @param jobId
	 * @return JobScheduler
	 */
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
