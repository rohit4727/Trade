package com.iris.batch.model;

/**
 * The Class FxMarketEvent.
 * 
 * @author Saurabh
 */
public abstract class TradeBase {

	private Long tradeId;
	private Long jobId;
	private Double tradePrice;
	private String security;
	private Long tradeSize;
	private String tradeDate;
	private String tradeTime;
	private double deviation;

	public TradeBase() {
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public Long getTradeSize() {
		return tradeSize;
	}

	public void setTradeSize(Long tradeSize) {
		this.tradeSize = tradeSize;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public double getDeviation() {
		return deviation;
	}

	public void setDeviation(double deviation) {
		this.deviation = deviation;
	}
}
