package com.iris.trade.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 
 * @author pushpendra.singh
 *
 */
@PropertySource(ignoreResourceNotFound = true, value = "classpath:trade_app.properties")
@Component
public class TradeAppPropertyUtil {

	@Value("${scheduleJobApiBaseUrl}")
	private String scheduleJobApiBaseUrl;
	
	@Value("${scheduleJobRestAPI}")
	private String scheduleJobRestAPI;

	@Value("${runJobRestAPI}")
	private String runJobRestAPI;

	@Value("${getAllJobScheduleDetails}")
	private String allJobScheduleDetails;

	@Value("${updateJobSchedulerDetail}")
	private String updateJobSchedulerDetail;
	
	@Value("${deleteJobScheduler}")
	private String deleteJobScheduler;
	
	@Value("${liveFeedBaseUri}")
	private String liveFeedBaseUri;
	
	@Value("${getLiveFeedData}")
	private String liveFeedData;
	
	@Value("${batchJobServiceBaseApi}")
	private String batchJobServiceBaseApi;
	
	@Value("${getAllScheduleJobProgressList}")
	private String allScheduleJobProgressList;

	public String getScheduleJobApiBaseUrl() {
		return scheduleJobApiBaseUrl;
	}

	public String getScheduleJobRestAPI() {
		return scheduleJobApiBaseUrl + scheduleJobRestAPI;
	}

	public String getRunJobRestAPI() {
		return scheduleJobApiBaseUrl + runJobRestAPI;
	}

	public String getAllJobScheduleDetails() {
		return scheduleJobApiBaseUrl + allJobScheduleDetails;
	}

	public String getUpdateJobSchedulerDetail() {
		return scheduleJobApiBaseUrl + updateJobSchedulerDetail;
	}

	public String getDeleteJobScheduler() {
		return scheduleJobApiBaseUrl + deleteJobScheduler;
	}

	public String getLiveFeedBaseUri() {
		return liveFeedBaseUri;
	}

	public String getLiveFeedData() {
		return liveFeedBaseUri + liveFeedData;
	}

	public String getBatchJobServiceBaseApi() {
		return batchJobServiceBaseApi;
	}

	public String getAllScheduleJobProgressList() {
		return batchJobServiceBaseApi + allScheduleJobProgressList;
	}

	

}
