package com.iris.trade.constants;

/**
 * 
 * @author pushpendra.singh
 *
 */
public interface IControllerConstants {

	public static final String TRADE_APP = "/TradeApp";
	public static final String SCHEDULE_OR_RUN_JOB = "/scheduleOrRunJob";
	public static final String GET_ALL_JOB_SCHEDULE_DETAILS = "/getAllJobScheduleDetails";
	public static final String UPDATE_JOB_SCHEDULE_DETAILS = "/updateJobScheduleDetails";
	public static final String DELETE_JOB_SCHEDULE_DETAIL = "/deleteJobScheduleDetail";
	public static final String GET_LIVE_FEED_DATA = "/getLiveFeedData/{security}";
	public static final String GET_ALL_SCHEDULE_JOB_PROG_LIST = "/getScheduleJobProgressList";
	public static final String GET_ALL_EXECUTED_JOB_LIST = "/getAllExecutedJobList";
	public static final String GET_SECURITY_LIST = "/getSecurityList";

	public static final String ID = "id";
	public static final String SECURITY = "security";
	public static final String SCHEDULE_JOB = "0";	
	public static final String SLASH = "/";
	public static final String SCHEDULE_JOB_SUCCESS = "Job Scheduled Successfully ! ";
	public static final String SCHEDULE_JOB_FAILURE = "Job Schedule Failed ! ";
	public static final String DELETE_SUCCESS = "Deleted Successfully ! ";
	public static final String DELETE_JOB_FAILED = "Delete Failed ! ";
	
	// Logger Messages
	public static final String SCHEDULE_JOB_EXCEPTION_MSG = "method : sheduleJob - Schedule or run Job Failed for JobName : {} ";
	public static final String UPDATE_JOB_EXCEPTION_MSG = "method : updateScheduleJobDetails - Update Failed for JobName : {} ";
	public static final String DELETE_JOB_EXCEPTION_MSG = "method : deleteScheduleJobDetail - Delete Failed for Job Id : {} ";
	public static final String GET_ALL_JOB_DETAILS_EXCEPTION_MSG = "method : getAllJobScheduleDetails - Error getting all job details";
	public static final String GET_LIVE_FEED_DATA_EXCEPTION_MSG = "method : getLiveFeedData - Failed for security : {} ";
	public static final String GET_PROGRESS_LIST_EXCEPTION_MSG = "method : getScheduleJobProgressList - Failed to load job progress list";
	public static final String GET_EXECUTED_LIST_EXCEPTION_MSG = "method : getAllExecutedJobList - to load job excuted list";
	public static final String GET_SECURITY_LIST_EXCEPTION_MSG = "method : getAllSecurityList - to load security list";


}
