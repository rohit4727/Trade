package com.iris.scheduler.constants;

public interface IControllerConstants {
	
	public static final String JOB_SCHEDULER = "/jobScheduler";
	public static final String GET_ALL_JOB_SCHEDULE_DETAILS = "/getAllJobScheduleDetails";
	public static final String CREATE_JOB_SCHEDULER = "/createJobScheduler";
	public static final String GET_JOB_SCHEDULER_BY_ID = "/getJobSchedulerById";
	public static final String ID_PARAM = "/{id}";
	public static final String UPDATE_JOB_SCHEDULER_DETAIL = "/updateJobSchedulerDetail";
	public static final String DELETE_JOB_SCHEDULER = "/deleteJobScheduler";
	public static final String RUN_JOB_SCHEDULER = "/runJobScheduler";
	public static final String SCHEDULE_JOB_SCHEDULER = "/schJobScheduler/";
	public static final String ID = "id";
	public static final String JOB_SCHEDULER_LBL = "JobScheduler";
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED TO RUN";
	public static final String FIND_JOB_SCHEDULER_BY_SCHEDULE_DATE = "/findJobSchedulerByScheduleDate/{scheduleDate}";
	public static final String SCHEDULE_DATE = "scheduleDate";
	public static final long SCHEDULE_TIMING = 1000;
	public static final String CRON_RUN = "cmd /c start ";
	public static final String DONE = "1";
	public static final String FAIL = "2";
	
	

}
