package com.iris.batchJobService.service;

import java.util.List;

import com.iris.batchJobService.entity.JobProgressData;

/*
 * Service interface
 * 
 * @author Rohit Elayathu
 */
public interface IJobProgressService {

	List<JobProgressData> getCompletedJobs();

	List<JobProgressData> getRunningJobs();
	
	JobProgressData saveJobProgress(JobProgressData jobProgressData);

}
