package com.iris.batch.util;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Utility class for providing constants from application.properties file
 *
 * @author Saurabh Gupta
 */
@Configuration
@PropertySource(value = "classpath:SpringBatchBootCustom.properties")
@ConfigurationProperties(prefix = "csvmapping")
public class PropertiesUtil {

	private int totalColumns, concurrencyLimit, linesToSkip;
	private String tradeFileName, insertionQuery, applicationUrl, liveTradeUrl, jobProgressServiceUrl;

	private Map<String, String> csvProperty;

	public int getTotalColumns() {
		return totalColumns;
	}

	public void setTotalColumns(int totalColumns) {
		this.totalColumns = totalColumns;
	}

	public int getConcurrencyLimit() {
		return concurrencyLimit;
	}

	public void setConcurrencyLimit(int concurrencyLimit) {
		this.concurrencyLimit = concurrencyLimit;
	}

	public int getLinesToSkip() {
		return linesToSkip;
	}

	public void setLinesToSkip(int linesToSkip) {
		this.linesToSkip = linesToSkip;
	}

	public String getTradeFileName() {
		return tradeFileName;
	}

	public void setTradeFileName(String tradeFileName) {
		this.tradeFileName = tradeFileName;
	}

	public String getInsertionQuery() {
		return insertionQuery;
	}

	public void setInsertionQuery(String insertionQuery) {
		this.insertionQuery = insertionQuery;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	public String getLiveTradeUrl() {
		return liveTradeUrl;
	}

	public void setLiveTradeUrl(String liveTradeUrl) {
		this.liveTradeUrl = liveTradeUrl;
	}

	public String getJobProgressServiceUrl() {
		return jobProgressServiceUrl;
	}

	public void setJobProgressServiceUrl(String jobProgressServiceUrl) {
		this.jobProgressServiceUrl = jobProgressServiceUrl;
	}

	public Map<String, String> getCsvProperty() {
		return csvProperty;
	}

	public void setCsvProperty(Map<String, String> csvProperty) {
		this.csvProperty = csvProperty;
	}
}
