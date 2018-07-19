package com.iris.scheduler.constants;

/**
 * 
 * @author pushpendra.singh
 * @author anchal.handa
 */
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

	// Loggers
	public static final String CHECKFILEPATHERROR = "Failed To Run Job With Path and Exception {}";
	public static final String CHECKFILEPATHINFO = "Path of Script Does Not Exists {}";
	public static final String RUNCMDERROR = "Failed To Run Cmd With Batchfilepath {}";
	public static final String RUNCMDINFO = "Batch file runs successfully {}";
	public static final String SAVESTATUSERROR = "Unable To Save The Status and Exception is";
	public static final String SAVESTATUSINFO = "Saved the Status Of Job";
	public static final String FINDJOBIDERROR = "Unable To Get The Job By Id and Exception is {}";
	public static final String FINDJOBIDINFO = "Find Job By ID {}";
	public static final String FINDJOBBYCURDATEERROR = "Unable To Get The Jobs By Currentdate and Exception {} ";
	public static final String FINDJOBBYCURDATEINFO =	"Find Jobs BY Its Timing {}";
	public static final String GETSCHEDULEDJOBERROR = "Failed To Get Scheduled Jobs and Exception is";
	public static final String SCHEDULEDJOBERROR = "Failed To Run scheduleJob With Name and Exception {}";
	public static final String CREATEJOBSCHEDULARERROR = "CreateJobScheduler : Create Job Failed for Run Job Having Name and Exception is {}";
	public static final String RUNJOBFAILLOGGER = "FAILED To Run The Job with ID {}";
	public static final String RUNJOBOKLOGGER = "RUNCMD with ID {}";
	public static final String CREATE_SCHEDULER_EXCEP_LOG_MSG = "createJobScheduler : create / schedule job faied for JobName: ";
	public static final String UPDATE_JOB_SCHEDULER_DETAILS_EXCEP_LOG_MSG = "updateJobSchedulerDetail : update schedule job faied for jobId: ";
	public static final String PATHNOTEXIST="BATCH FILE PATH DOES NOT EXISTS";
}
