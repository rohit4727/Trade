package com.iris.batch.step;

import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.iris.batch.util.PropertiesUtil;

/**
 * AsyncTaskExecuter for parallel processing of spring batch tasks
 *
 * @author Saurabh Gupta
 */
public class TaskExecuter extends SimpleAsyncTaskExecutor {
	private static final long serialVersionUID = 1L;

	public TaskExecuter(PropertiesUtil props) {
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("Thread-");
		asyncTaskExecutor.setConcurrencyLimit(props.getConcurrencyLimit());
	}
}
