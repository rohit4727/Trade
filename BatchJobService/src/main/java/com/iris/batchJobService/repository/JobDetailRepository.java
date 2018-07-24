package com.iris.batchJobService.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iris.batchJobService.entity.ScheduleJobDetails;

/*
 * Repository interface for SCHEDULE_JOB_DETAILS table
 * 
 * @author Rohit Elayathu
 */
public interface JobDetailRepository extends CrudRepository<ScheduleJobDetails, Long> {

	public List<ScheduleJobDetails> findByJobProgressStatusIn(List<Integer> status);

}
