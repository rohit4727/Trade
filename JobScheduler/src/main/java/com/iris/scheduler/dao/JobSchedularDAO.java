package com.iris.scheduler.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iris.scheduler.entity.JobScheduler;

public interface JobSchedularDAO {

	public List<JobScheduler> findbycurDate();

	public JobScheduler findbyjobId(@Param("id") Long id);

}
