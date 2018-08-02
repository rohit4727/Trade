package com.iris.batch.util;

/**
 * Error message constants
 *
 * @author Saurabh Gupta
 */
public interface ErrorMsg {
	String INSERTION_QUERY_NOT_FOUND = "Insertion query is not defined in application.properties";
	String CHUNK_SIZE_NOT_FOUND = "chunk_size not found in application.properties file";
	String CHUNK_NOT_INT = "Chunk size is not integer";
	String CONCURRENCY_LIMIT_NOT_FOUND = "concurrency_limit not found in application.properties file";
	String CONCURRENCY_LIMIT_NOT_INT = "Concurrency limit is not integer value";
	String TOTAL_CAL_NOT_FOUND = "csvmapping.totalColumns is not found in properties file";
	String NOT_FOUND_IN_PROP_FILE = " not found in application.properties";
	String TOTAL_COL_NOT_INT = "csvmapping.totalColumns is not integer value in application.properties";
	String JOB_ID_NOT_PROVIDED = "Please provide jobId as argument";
	String CUSTOM_STEP_LISTNER_FILE_NO_FOUND = "FileNotFoundException in CustomStepListener BeforeStep";
	String CUSTOM_STEP_LISTNER_IO_EXCEPTION = "IOException in CustomStepListener BeforeStep";
	String CUSTOM_CHUNK_LISTNER_ERROR = "CustomChunkListener afterChunkError called";
	String KAFKA_MAP_ERROR_STRING = "Best value not found for trade id=";
}
