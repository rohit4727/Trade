package com.iris.kafkaproducer.util;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value="file:mapping.properties")
//classpath:mapping.properties")
@ConfigurationProperties(prefix="csvmapping")
public class CSVMapping {

	private int totalColumns;
	private String fileName;
	
	private Map<Integer, String> csvProperty;

	public Map<Integer, String> getCsvProperty() {
		return csvProperty;
	}

	public void setCsvProperty(Map<Integer, String> csvProperty) {
		this.csvProperty = csvProperty;
	}

	public int getTotalColumns() {
		return totalColumns;
	}

	public void setTotalColumns(int totalColumns) {
		this.totalColumns = totalColumns;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
