package com.iris.batchJobService.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iris.batchJobService.entity.JobProgressData;

/*
 * Repository interface 
 * 
 * @author Rohit Elayathu
 */
@Repository
public interface JobProgressRepository extends CrudRepository<JobProgressData, Integer> {

	public List<JobProgressData> findByStatusIn(List<Integer> status);

}
