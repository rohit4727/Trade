package com.iris.trade.bean;

import java.util.Date;

public class JobProgressData {
	
	private Long id;
	private String jobName;
	private String batchFilePath;
	private Date scheduleDate;
	private String jobProgresstatus;
	private String status;
	private int totalLineCount;
	private int writerLineCount;
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
	public String getJobProgresstatus() {
		return jobProgresstatus;
	}
	public void setJobProgresstatus(String jobProgresstatus) {
		this.jobProgresstatus = jobProgresstatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotalLineCount() {
		return totalLineCount;
	}
	public void setTotalLineCount(int totalLineCount) {
		this.totalLineCount = totalLineCount;
	}
	public int getWriterLineCount() {
		return writerLineCount;
	}
	public void setWriterLineCount(int writerLineCount) {
		this.writerLineCount = writerLineCount;
	}
	
}
