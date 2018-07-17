package com.iris.batch.util;

public interface ETLConstants {
	String jobName = "CITI Market Risk";
	String stepName = "Extract -> Transform -> Aggregate -> Load";
	String totalColumn = "csvmapping.totalColumns";
	String column = "csvmapping.column";
	String tradeClassName = "Trade";
	String concurrencyLimit = "concurrency_limit";
	String chunkSize = "chunk_size";
	int LINES_TO_SKIP = 1;
	
	// Job Status
	int JOB_RUNNING = 0;
	int JOB_COMPLETED = 1;
	int JOB_FAILED = 2;

}
