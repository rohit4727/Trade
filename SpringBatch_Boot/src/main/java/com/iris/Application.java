package com.iris;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * The Class Application.
 * 
 * @author Satyveer
 */

@SpringBootApplication
@EnableBatchProcessing
@ImportResource("classpath:batchjob.xml")
public class Application {
	static public void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}