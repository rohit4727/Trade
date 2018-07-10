package com.iris.scheduler.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iris.scheduler.entity.JobScheduler;

public interface CronRepository extends CrudRepository<JobScheduler, Long>{
	
	@Query("SELECT p FROM JobScheduler p WHERE p.scheduleDate<=now() and p.status=0")
	
	public List<JobScheduler> findbycurDate();
	@Query("SELECT p FROM JobScheduler p WHERE p.id=:id")
    public JobScheduler findbyjobId(@Param("id") Long id);

}
