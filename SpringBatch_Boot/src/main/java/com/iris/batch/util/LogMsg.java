package com.iris.batch.util;

/**
 * Constants for logging purposes
 *
 * @author Saurabh Gupta
 */
public interface LogMsg {
	String CUSTOMER_STEP_LISTNER_BEFORE_STEP_SUCCESS = "CustomStepListener beforeStep ran successfully : ";
	String CUSTOMER_STEP_LISTNER_AFTER_STEP_SUCCESS = "CustomStepListener afterStep ran successfully : ";
	String CUSTOMER_CHUNK_LISTNER_AFTER_CHUNK_SUCCESS = "CustomChunkListener afterChunk ran successfully : ";
	String TEST_JOB_SUCCESS = "Spring batch job has been run successfully with jobId=";
	String UNABLE_TO_READ_PROPERTY_FILE = "Unable to read property file";
	String EXIT_STATUS = "Exit Status : ";
}
