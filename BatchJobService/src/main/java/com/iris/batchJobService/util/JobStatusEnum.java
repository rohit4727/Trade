package com.iris.batchJobService.util;

/*
 * This enum contains batch job status constants
 * 
 * @author Rohit Elayathu
 */
public enum JobStatusEnum {

	JOB_RUNNING(0), JOB_COMPLETED(1), JOB_FAILED(2);

	private final int value;

	JobStatusEnum(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}
}
