package com.iris.scheduler.service;

import com.iris.scheduler.entity.JobScheduler;

public interface SchedularServiceImpl {
	
	public boolean checkfilepath(String filepath);
	public void runcmd(JobScheduler job,boolean flag);

}
