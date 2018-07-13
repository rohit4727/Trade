package com.iris.batchJobService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iris.batchJobService.entity.JobProgressData;

/*
 * DAO interface 
 * 
 * @author Rohit Elayathu
 */
@Repository
public interface IJobProgressDao extends CrudRepository<JobProgressData, Integer> {

	@Query("SELECT p FROM JobProgressData p WHERE p.status=:status")
	public List<JobProgressData> getJobsByStatus(@Param("status") Integer status);

}
