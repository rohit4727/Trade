package com.iris.scheduler.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iris.scheduler.entity.JobScheduler;

@Repository
public interface JobSchedulerRepository extends JpaRepository<JobScheduler, Long>{
	
	public List<JobScheduler> findByScheduleDate(Date scheduleDate);}
