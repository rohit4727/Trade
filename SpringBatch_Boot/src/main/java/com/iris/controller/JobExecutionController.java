package com.iris.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iris.batch.util.LogMsg;
import com.iris.mvc.service.SpringBatchService;

/**
 * JobExecutionController class
 *
 * @author Saurabh Gupta
 */
@RestController
@RequestMapping("/")
public class JobExecutionController {

	private static final Logger log = LoggerFactory.getLogger(JobExecutionController.class);

	@Autowired
	private SpringBatchService batchService;

	@GetMapping(value = "runjob/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> runjob(@PathVariable("jobId") Long jobId) {

		boolean status = batchService.runJob(jobId);

		log.debug(LogMsg.TEST_JOB_SUCCESS + jobId);

		Map<String, Object> result = new HashMap<>();
		result.put("status", status);
		return result;
	}
}
