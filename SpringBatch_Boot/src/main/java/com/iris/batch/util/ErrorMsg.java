package com.iris.batch.util;

public interface ErrorMsg {
	String insertionQueryNotFound = "Insertion query is not defined in application.properties";
	String chunkSizeNotFound = "chunk_size not found in application.properties file";
	String chunkNotInt = "Chunk size is not integer";
	String concurrencyLimitNotFound = "concurrency_limit not found in application.properties file";
	String concurrencyLimitNotInt = "Concurrency limit is not integer value";
	String totalColNotFound = "csvmapping.totalColumns is not found in properties file";
	String notFoundInPropFile = " not found in application.properties";
	String totalColNotInt = "csvmapping.totalColumns is not integer value in application.properties";
	String jobIdNotProvided = "Please provide jobId as argument";
	String customStepListenerFileNotFoundException = "FileNotFoundException in CustomStepListener BeforeStep";
	String customStepListenerIOException = "IOException in CustomStepListener BeforeStep";
	String customChunkListenerError = "CustomChunkListener afterChunkError called";
}
