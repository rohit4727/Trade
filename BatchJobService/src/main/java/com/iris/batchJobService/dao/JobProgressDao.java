package com.iris.batchJobService.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iris.batchJobService.entity.JobProgressData;
import com.iris.batchJobService.repository.JobProgressRepository;

@Repository
public class JobProgressDao implements IJobProgressDao {

	@Autowired
	private JobProgressRepository jobProgressRepository;
	
	public JobProgressRepository getJobProgressRepository() {
		return jobProgressRepository;
	}

	public void setJobProgressRepository(JobProgressRepository jobProgressRepository) {
		this.jobProgressRepository = jobProgressRepository;
	}
	
	@Override
	public List<JobProgressData> getJobProgressByStatus(List<Integer> statusList) {
		List<JobProgressData> list = getJobProgressRepository().findByStatusIn(statusList);

		return list;
	}

	@Override
	public JobProgressData saveJobProgress(JobProgressData jobProgressData) {
		JobProgressData jobProgressDataDb = null;

		if (getJobProgressRepository().existsById(jobProgressData.getJobId())) {
			jobProgressDataDb = getJobProgressRepository().findById(jobProgressData.getJobId()).get();

			if (jobProgressData.getTotalLineCount() != null) {
				jobProgressDataDb.setTotalLineCount(jobProgressData.getTotalLineCount());
			}

			if (jobProgressData.getWriterLineCount() != null) {
				jobProgressDataDb.setWriterLineCount(jobProgressData.getWriterLineCount());
			}
			
			if (jobProgressData.getStatus() != null) {
				jobProgressDataDb.setStatus(jobProgressData.getStatus());
			}

		} else {
			jobProgressDataDb = jobProgressData;
		}

		JobProgressData JobProgressDataSaved = getJobProgressRepository().save(jobProgressDataDb);
		
		return JobProgressDataSaved;
	}

}
