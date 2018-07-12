package com.iris.batchJobService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Entity class for table  JOB_PROGRESS_DATA
 * 
 * @author Rohit Elayathu
 */
@Entity
@Table(name = "JOB_PROGRESS_DATA")
public class JobProgressData {

	@Id
	@Column(name = "job_id")
	private int jobId;

	@Column(name = "total_line_count")
	private Integer totalLineCount;

	@Column(name = "writer_line_count")
	private Integer writerLineCount;

	@Column(name = "status")
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
