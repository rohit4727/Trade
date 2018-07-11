package com.iris.batch.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * The Class FxMarketEvent.
 * 
 * @author Satyveer
 */
public abstract class TradeBase {

	private long tradeid;
	private long jobId;
	private double tradePrice;
	private String security;
	private String tradeSize;
	private Date tradeDate;
	private Timestamp tradeTime;
	private double deviation;

	public TradeBase() {
	}

	public long getTradeid() {
		return tradeid;
	}

	public void setTradeid(long tradeid) {
		this.tradeid = tradeid;
	}

	public double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getTradeSize() {
		return tradeSize;
	}

	public void setTradeSize(String tradeSize) {
		this.tradeSize = tradeSize;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Timestamp getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Timestamp tradeTime) {
		this.tradeTime = tradeTime;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public double getDeviation() {
		return deviation;
	}

	public void setDeviation(double deviation) {
		this.deviation = deviation;
	}

}
