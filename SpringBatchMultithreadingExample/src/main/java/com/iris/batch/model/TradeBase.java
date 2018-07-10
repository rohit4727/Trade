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
	private double tradePrice;
	private String security;
	private String tradeSize;
	private Date tradeDate;
	private Timestamp tradeTime;

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

	// public MrMarketEvent(String tradeid, String tradePrice, String security,
	// String instrumentType,
	// String counterParty, String tradeSize, String tradeDate, String tradeTime,
	// String currency,
	// String broker, String direction, String tradeAlias, String contoller, String
	// clearingSystem, String clearinghous) {
	// super();
	// this.tradeid = tradeid;
	// this.tradePrice = tradePrice;
	// this.security = security;
	// this.instrumentType = instrumentType;
	// this.counterParty = counterParty;
	// this.tradeSize = tradeSize;
	// this.tradeDate = tradeDate;
	// this.tradeTime = tradeTime;
	// this.currency = currency;
	// this.broker = broker;
	// this.direction = direction;
	// this.tradeAlias = tradeAlias;
	// this.contoller = contoller;
	// this.clearingSystem = clearingSystem;
	// this.clearinghous = clearinghous;
	// }

	// @Override
	// public String toString() {
	// return "MrMarketEvent [tradeid=" + tradeid + ", tradePrice=" + tradePrice +
	// ", security=" + security
	// + ", instrumentType=" + instrumentType + ", counterParty=" + counterParty +
	// ", tradeSize=" + tradeSize
	// + ", tradeDate=" + tradeDate + ", currency=" + currency + ", broker=" +
	// broker + ", direction="
	// + direction + ", tradeAlias=" + tradeAlias + ", contoller=" + contoller + ",
	// clearingSystem="
	// + clearingSystem + ", clearinghous=" + clearinghous + "]";
	// }

}
