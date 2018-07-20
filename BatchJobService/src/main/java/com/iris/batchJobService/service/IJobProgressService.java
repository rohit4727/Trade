package com.iris.batchJobService.service;

import java.util.List;

import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.entity.ScheduleJobDetails;

/*
 * Service interface
 * 
 * @author Rohit Elayathu
 */
public interface IJobProgressService {

	List<ScheduleJobDetails> getCompletedJobs();

	List<ScheduleJobDetails> getRunningJobs();
	
	JobProgressData saveJobProgress(JobProgressData jobProgressData);

}
