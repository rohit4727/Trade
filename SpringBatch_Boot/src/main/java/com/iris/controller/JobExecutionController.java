package com.iris.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iris.mvc.service.SpringBatchService;

@RestController
@RequestMapping("/")
public class JobExecutionController {

	private static final Logger log = LoggerFactory.getLogger(JobExecutionController.class);
	private static final String JOB_SUCCESS = "Spring batch job has been run successfully with jobId=";

	@Autowired
	private SpringBatchService batchService;

	@RequestMapping("runjob/{jobId}")
	public Map<String, Object> runjob(@PathVariable("jobId") Long jobId) {

		boolean status = batchService.runJob(jobId);

		log.debug(JOB_SUCCESS + jobId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("status", status);
		return result;
	}
}
