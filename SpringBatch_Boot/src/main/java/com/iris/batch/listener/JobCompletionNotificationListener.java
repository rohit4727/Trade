package com.iris.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
 * The Class JobCompletionNotificationListener
 *
 * @author Satyveer
 */
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

//	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	@Override
	public void afterJob(JobExecution jobExecution) {
		// code for after job completion
	}
}
