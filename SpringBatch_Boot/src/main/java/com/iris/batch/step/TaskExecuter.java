package com.iris.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.iris.batch.util.PropertiesUtil;

/**
 * AsyncTaskExecuter for parallel processing of spring batch tasks
 *
 * @author Saurabh Gupta
 */
public class TaskExecuter extends SimpleAsyncTaskExecutor {
	private static final Logger log = LoggerFactory.getLogger(TaskExecuter.class);
	private static final long serialVersionUID = 1L;
	private static final String CONCURRENCY_LIMIT = "concurrencyLimit";

	public TaskExecuter() {

		int concurrency = 0;
		try {
			concurrency = Integer.parseInt(PropertiesUtil.get(CONCURRENCY_LIMIT));
		} catch (NumberFormatException e) {
			log.error("Error", e);
			throw new RuntimeException(e);
		}
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("Thread-");
		asyncTaskExecutor.setConcurrencyLimit(concurrency);
	}
}
