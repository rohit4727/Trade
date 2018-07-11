package com.iris.trade.bean;

import java.util.Date;

public class JobScheduler {
	
	private Long id;
	private String jobName;
	private String batchFilePath;
	private Date scheduleDate;
	private String status;
	private String runFrequency;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getBatchFilePath() {
		return batchFilePath;
	}
	public void setBatchFilePath(String batchFilePath) {
		this.batchFilePath = batchFilePath;
	}
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRunFrequency() {
		return runFrequency;
	}
	public void setRunFrequency(String runFrequency) {
		this.runFrequency = runFrequency;
	}
	
	
}
