package com.iris.scheduler.constants;

public interface IControllerConstants {
	
	public static final String JOB_SCHEDULER = "/jobScheduler";
	public static final String GET_ALL_JOB_SCHEDULE_DETAILS = "/getAllJobScheduleDetails";
	public static final String CREATE_JOB_SCHEDULER = "/createJobScheduler";
	public static final String GET_JOB_SCHEDULER_BY_ID = "/getJobSchedulerById/{id}";
	public static final String UPDATE_JOB_SCHEDULER_DETAIL = "/updateJobSchedulerDetail/{id}";
	public static final String DELETE_JOB_SCHEDULER = "/deleteJobScheduler/{id}";
	public static final String RUN_JOB_SCHEDULER = "/runJobScheduler/{id}";
	public static final String SCHEDULE_JOB_SCHEDULER = "/schJobScheduler/";
	public static final String ID = "id";
	public static final String JOB_SCHEDULER_LBL = "JobScheduler";
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED To RUN";
	public static final String FIND_JOB_SCHEDULER_BY_SCHEDULE_DATE = "/findJobSchedulerByScheduleDate/{scheduleDate}";
	public static final String SCHEDULE_DATE = "scheduleDate";
	
	

}
