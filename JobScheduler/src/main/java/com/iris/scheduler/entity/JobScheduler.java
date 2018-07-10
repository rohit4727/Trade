package com.iris.scheduler.entity;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "schedule_job_details")
public class JobScheduler {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String jobName;
	
	@NotBlank
	private String batchFilePath;
	
	@Column(nullable = false)
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
	
}
