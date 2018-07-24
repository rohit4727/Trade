package com.iris.batchJobService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Entity class for JOB_PROGRESS_DATA table
 * 
 * @author Rohit Elayathu
 */
@Entity
@Table(name = "JOB_PROGRESS_DATA")
public class JobProgressData {

	@Id
	@Column(name = "job_id")
	private Long jobId;

	@Column(name = "total_line_count")
	private Integer totalLineCount;

	@Column(name = "writer_line_count")
	private Integer writerLineCount;

	@Column(name = "status")
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
