package com.iris.mvc.service;

/**
 * Service Layer: SpringBatchService
 *
 * @author Saurabh Gupta
 */
public interface SpringBatchService {

	public boolean runJob(Long jobId);
}
