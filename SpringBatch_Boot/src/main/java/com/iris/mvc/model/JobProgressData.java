package com.iris.mvc.model;

public class JobProgressData {
	
	private Long jobId;

	private Integer totalLineCount;

	private Integer writerLineCount;

	private Integer status;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Integer getTotalLineCount() {
		return totalLineCount;
	}

	public void setTotalLineCount(Integer totalLineCount) {
		this.totalLineCount = totalLineCount;
	}

	public Integer getWriterLineCount() {
		return writerLineCount;
	}

	public void setWriterLineCount(Integer writerLineCount) {
		this.writerLineCount = writerLineCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
