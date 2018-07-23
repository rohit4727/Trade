package com.iris.batchJobService.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iris.batchJobService.entity.ScheduleJobDetails;

public interface JobDetailRepository extends CrudRepository<ScheduleJobDetails, Long> {

	public List<ScheduleJobDetails> findByJobProgressStatusIn(List<Integer> status);

}
