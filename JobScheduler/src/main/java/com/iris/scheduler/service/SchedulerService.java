package com.iris.scheduler.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.iris.scheduler.entity.JobScheduler;

public interface SchedulerService {
	
	public boolean checkfilepath(String filepath);
	public void runcmd(JobScheduler job,boolean flag);
	public void savestatus(JobScheduler job);
	public JobScheduler findbyjobId(Long id);
	public List<JobScheduler> findbycurDate();
	

}
