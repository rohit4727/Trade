package com.iris.scheduler.service;

import java.util.List;
import com.iris.scheduler.entity.JobScheduler;

public interface JobSchedulerDetailService {
	
	public List<JobScheduler> getAllJobScheduleDetails();
	public JobScheduler createOrUpdateJobScheduler(JobScheduler jobScheduler);
	public JobScheduler getJobSchedulerById(Long jobId);
	public void deleteJobScheduler(JobScheduler jobScheduler);

}
