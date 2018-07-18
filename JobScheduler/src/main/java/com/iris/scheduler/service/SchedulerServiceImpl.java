package com.iris.scheduler.service;

import java.io.File;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.dao.JobSchedularDAO;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.repository.CronRepository;
import com.iris.scheduler.repository.JobSchedulerRepository;

/**
 * @author anchal.handa
 *
 */

@Service
public class SchedulerServiceImpl implements SchedulerService {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	@Autowired
	private JobSchedularDAO jobSchedularDAO;
	@Autowired
	private JobSchedulerRepository jobSchedulerRepository;


	 @Override
	public boolean checkfilepath(String path) {
		 
		try {
			File f = new File(path);
			if (f.exists() && !f.isDirectory()) {
				logger.info(IControllerConstants.CHECKFILEPATHINFO,path);
				return true;
			}
		} catch (Exception e) {

			logger.error(IControllerConstants.CHECKFILEPATHERROR, path , e);
		}
		logger.info(IControllerConstants.PATHNOTEXIST,path);
		return false;

	}

	
	@Override
	public void runcmd(String batchfilepath, Long id) {

		try {

			String cronrun = IControllerConstants.CRON_RUN + batchfilepath + " " + id;
			Runtime.getRuntime().exec(cronrun);
			logger.info(IControllerConstants.RUNCMDINFO,cronrun);
		} catch (Exception e) {
			logger.error(
					IControllerConstants.RUNCMDERROR, batchfilepath,e);
		}

	}

	
	@Override
	public void savestatus(JobScheduler job) {
		try {
			logger.info(IControllerConstants.SAVESTATUSINFO);
			jobSchedulerRepository.save(job);
		} catch (Exception e) {
			logger.error(IControllerConstants.SAVESTATUSERROR , e);
		}

	}

	
	@Override
	public JobScheduler findbyjobId(Long id) {
		
		try {
			JobScheduler Job=jobSchedularDAO.findbyjobId(id);
			logger.info(IControllerConstants.FINDJOBIDINFO,id);
			return Job;
		} catch (Exception e) {
			logger.error(IControllerConstants.FINDJOBIDERROR , id , e);
		}
		return null;
	}

	
	@Override
	public List<JobScheduler> findbycurDate() {
		try {
			List<JobScheduler> listJob= jobSchedularDAO.findbycurDate();
			logger.info(IControllerConstants.FINDJOBBYCURDATEINFO,listJob.size());
			return listJob;
		} catch (Exception e) {
			logger.error(IControllerConstants.FINDJOBBYCURDATEERROR , e);
		}
		return null;
	}

}
