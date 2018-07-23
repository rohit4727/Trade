package com.iris.batchJobService.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iris.batchJobService.entity.JobProgressData;

/*
 * Repository interface for JOB_PROGRESS_DATA table 
 * 
 * @author Rohit Elayathu
 */
public interface JobProgressRepository extends CrudRepository<JobProgressData, Long> {

	public List<JobProgressData> findByStatusIn(List<Integer> status);

}
