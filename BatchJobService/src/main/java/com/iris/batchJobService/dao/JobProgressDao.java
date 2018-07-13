package com.iris.batchJobService.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iris.batchJobService.entity.JobProgressData;

/*
 * DAO impl 
 * 
 * @author Rohit Elayathu
 */
@Transactional
@Repository
public class JobProgressDao implements IJobProgressDao {

	@PersistenceContext
	private EntityManager entityManager;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * Get job list by status
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<JobProgressData> getJobsByStatus(int status) {
		String hql = "FROM JobProgressData as jpd where jpd.status = :status ORDER BY jpd.jobId";
		Query q = entityManager.createQuery(hql);
		q.setParameter("status", status);

		logger.info("--Inside getJobsByStatus : status : " + status + "--");

		return (List<JobProgressData>) q.getResultList();
	}

}
