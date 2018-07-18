package com.iris.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.iris.batch.util.ETLConstants;
import com.iris.batch.util.ErrorMsg;
import com.iris.batch.util.PropertiesUtil;

/**
 * AsyncTaskExecuter for parallel processing of spring batch tasks
 *
 * @author Saurabh Gupta
 */
public class TaskExecuter extends SimpleAsyncTaskExecutor {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(TaskExecuter.class);

	public TaskExecuter() {
		int concurrencyInt = Integer.MAX_VALUE;

		try {
			String concurrency = PropertiesUtil.get(ETLConstants.CONCURRENCY_LIMIT);
			if (concurrency == null || concurrency.isEmpty()) {
				log.error(ErrorMsg.CONCURRENCY_LIMIT_NOT_FOUND);
				throw new RuntimeException();
			}

			concurrencyInt = Integer.parseInt(concurrency);

		} catch (NumberFormatException e) {
			log.error(ErrorMsg.CONCURRENCY_LIMIT_NOT_INT);
		}

		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("Thread-");
		asyncTaskExecutor.setConcurrencyLimit(concurrencyInt);
	}
}
