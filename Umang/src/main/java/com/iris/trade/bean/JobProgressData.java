package com.iris.trade.bean;

/**
 * 
 * @author pushpendra.singh
 *
 */
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

	public void setStatus(Integer status) {
		this.status = status;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
