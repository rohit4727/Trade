package com.iris.batchJobService.util;

/*
 * This enum contains response bean constants
 * 
 * @author Rohit Elayathu
 */
public enum ResponseBeanStatusEnum {

	SUCCESS("SUCCESS"), FAILED("FAILED");

	private final String value;

	ResponseBeanStatusEnum(final String newValue) {
		value = newValue;
	}

	public String getValue() {
		return value;
	}
}
