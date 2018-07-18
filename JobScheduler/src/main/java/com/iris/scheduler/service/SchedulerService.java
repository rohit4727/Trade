package com.iris.scheduler.service;

import java.util.List;

import com.iris.scheduler.entity.JobScheduler;

/**
 * @author anchal.handa
 */
public interface SchedulerService {

	/*
	 * Check the file path/Job script path if exist
	 */
	public boolean checkfilepath(String filepath);
	/*
	 * Runs the batch file of different tasks/scripts with job id and job script
	 * path
	 */
	public void runcmd(String filepath, Long id);
	/*
	 * To Save the status of Job in Database
	 * 1 - Run successfully 
	 * 2 - Failure
	 * 0 - To be process as per scheduler time
	 */
	public void savestatus(JobScheduler job);
	/*
	 * Find the Jobs by Id
	 * 
	 */
	public JobScheduler findbyjobId(Long id);
	/*
	 * Find the Jobs by CURRENT DATETIME
	 * 
	 */
	public List<JobScheduler> findbycurDate();

}
