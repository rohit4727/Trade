package com.iris.scheduler.entity;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "schedule_job_details")
public class JobScheduler {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO, 
	    generator="native"
	)
	@GenericGenerator(
	    name = "native", 
	    strategy = "native"
	)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@NotBlank
	@Column(name = "job_name", unique = true)
	private String jobName;
	
	@NotBlank
	@Column(name = "batch_file_path")
	private String batchFilePath;
	
	@Column(name = "schedule_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date scheduleDate;
	
	@NotBlank
	private String status;

	public Long getId() {
		return id;
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

	
}
