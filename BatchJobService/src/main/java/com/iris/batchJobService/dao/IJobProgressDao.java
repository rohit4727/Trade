package com.iris.batchJobService.dao;

import java.util.List;

import com.iris.batchJobService.entity.JobProgressData;

public interface IJobProgressDao {

	public List<JobProgressData> getJobProgressByStatus(List<Integer> statusList);

	public JobProgressData saveJobProgress(JobProgressData jobProgressData);
}
