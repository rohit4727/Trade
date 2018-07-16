package com.iris.scheduler.service;

import java.util.List;
import com.iris.scheduler.entity.JobScheduler;

public interface JobSchedulerDetailService {
	
	/**
	 * This method will get all sheduled job list
	 * @return List<JobScheduler>
	 */
	public List<JobScheduler> getAllJobScheduleDetails();
	
	/**
	 * This will create or update job schedule details
	 * @param jobScheduler
	 * @return JobScheduler
	 */
	public JobScheduler createOrUpdateJobScheduler(JobScheduler jobScheduler);
	
	/**
	 * This method will get schedule job details by Id
	 * @param jobId
	 * @return JobScheduler
	 */
	public JobScheduler getJobSchedulerById(Long jobId);
	
	/**
	 * This method will delete job scheduler details
	 * @param jobScheduler
	 */
	public void deleteJobScheduler(JobScheduler jobScheduler);

}
