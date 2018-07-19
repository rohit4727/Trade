package com.iris.trade.bean;

/**
 * 
 * @author pushpendra.singh
 *
 */
public class JobProgressData {

	private int jobId;

	private Integer totalLineCount;

	private Integer writerLineCount;

	private Integer status;

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
