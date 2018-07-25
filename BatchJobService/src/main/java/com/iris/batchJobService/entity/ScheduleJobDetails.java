package com.iris.batchJobService.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * Entity class for SCHEDULE_JOB_DETAILS table
 * 
 * @author Rohit Elayathu
 *
 */
@Entity
@Table(name = "schedule_job_details")
@SecondaryTables({
		@SecondaryTable(name = "JOB_PROGRESS_DATA", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "job_id") }) })
public class ScheduleJobDetails {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "job_name", unique = true)
	private String jobName;

	@Column(name = "batch_file_path")
	private String batchFilePath;

	@Column(name = "schedule_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date scheduleDate;

	@Column(name = "status")
	private String status;

	@Column(table = "JOB_PROGRESS_DATA", name = "status")
	private Integer jobProgressStatus;

	@Column(table = "JOB_PROGRESS_DATA", name = "total_line_count")
	private Integer totalLineCount;

	@Column(table = "JOB_PROGRESS_DATA", name = "writer_line_count")
	private Integer writerLineCount;

	public ScheduleJobDetails() {
	}

	public ScheduleJobDetails(String jobName, String batchFilePath, Date scheduleDate, String status) {
		this.jobName = jobName;
		this.batchFilePath = batchFilePath;
		this.scheduleDate = scheduleDate;
	}

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

	public Integer getJobProgressStatus() {
		return jobProgressStatus;
	}

	public void setJobProgressStatus(Integer jobProgresstatus) {
		this.jobProgressStatus = jobProgresstatus;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
