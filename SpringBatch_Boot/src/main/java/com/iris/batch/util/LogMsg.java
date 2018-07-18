package com.iris.batch.util;

/**
 * Constants for logging purposes
 *
 * @author Saurabh Gupta
 */
public interface LogMsg {
	String CUSTOMER_STEP_LISTNER_BEFORE_STEP_SUCCESS = "CustomStepListener beforeStep ran succeefully : ";
	String CUSTOMER_STEP_LISTNER_AFTER_STEP_SUCCESS = "CustomStepListener afterStep ran succeefully : ";
	String CUSTOMER_CHUNK_LISTNER_AFTER_CHUNK_SUCCESS = "CustomChunkListener afterChunk ran succeefully : ";
	String TEST_JOB_SUCCESS = "Spring batch job has been run successfully with jobId=";
	String UNABLE_TO_READ_PROPERTY_FILE = "Unable to read property file";
}
