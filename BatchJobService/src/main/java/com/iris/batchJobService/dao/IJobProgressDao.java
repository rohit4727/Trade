package com.iris.batchJobService.dao;

import java.util.List;

import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.entity.ScheduleJobDetails;

/*
 * DAO interface
 * 
 * @author Rohit Elayathu
 */
public interface IJobProgressDao {

	public List<ScheduleJobDetails> getJobProgressByStatus(List<Integer> statusList);

	public JobProgressData saveJobProgress(JobProgressData jobProgressData);
}
