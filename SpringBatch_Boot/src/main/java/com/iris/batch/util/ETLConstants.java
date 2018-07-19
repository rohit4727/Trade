package com.iris.batch.util;

/**
 * Application constants These constants are used in through out the application
 *
 * @author Saurabh Gupta
 */
public interface ETLConstants {
	String JOB_NAME = "CITI Market Risk";
	String STEP_NAME = "Extract -> Transform -> Aggregate -> Load";
	String TOTAL_COLUMN = "csvmapping.totalColumns";
	String COLUMN = "csvmapping.column";
	String TRADE_CLASS_NAME = "Trade";
	String CONCURRENCY_LIMIT = "concurrency_limit";
	String CHUNK_SIXE = "chunk_size";
	int LINES_TO_SKIP = 1;

	// Job Status
	int JOB_RUNNING = 0;
	int JOB_COMPLETED = 1;
	int JOB_FAILED = 2;

	// Entity table
	String TRADE_TABLE = "trade_live_feed_copy";

}
