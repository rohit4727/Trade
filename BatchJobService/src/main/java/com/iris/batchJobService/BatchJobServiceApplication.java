package com.iris.batchJobService;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchJobServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(BatchJobServiceApplication.class);
			
	public static void main(String[] args) {
		SpringApplication.run(BatchJobServiceApplication.class, args);
		
		logger.info("--Application Started--");
	}
}
