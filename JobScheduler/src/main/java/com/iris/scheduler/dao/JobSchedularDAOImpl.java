package com.iris.scheduler.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.repository.CronRepository;

@Repository
public class JobSchedularDAOImpl implements JobSchedularDAO {

	@Autowired
	private CronRepository cronRepository;

	@Override
	public List<JobScheduler> findbycurDate() {

		return cronRepository.findbycurDate();
	}

	@Override
	public JobScheduler findbyjobId(Long id) {

		return cronRepository.findbyjobId(id);
	}

}
