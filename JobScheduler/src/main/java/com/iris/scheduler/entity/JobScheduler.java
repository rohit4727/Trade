package com.iris.scheduler.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author pushpendra.singh
 *
 */
@Entity
@Table(name = "schedule_job_details")
public class JobScheduler implements Serializable {

	private static final long serialVersionUID = -6334117912831273874L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
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

	public JobScheduler() {

	}

	public JobScheduler(String jobName, String batchFilePath, Date scheduleDate, String status) {
		this.jobName = jobName;
		this.batchFilePath = batchFilePath;
		this.scheduleDate = scheduleDate;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}
