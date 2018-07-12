package com.iris.batchJobService.dao;

import java.util.List;

import com.iris.batchJobService.entity.JobProgressData;

/*
 * DAO interface 
 * 
 * @author Rohit Elayathu
 */
public interface IJobProgressDao {

	List<JobProgressData> getJobsByStatus(int status);

}
