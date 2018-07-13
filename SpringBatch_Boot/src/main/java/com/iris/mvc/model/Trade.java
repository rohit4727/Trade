package com.iris.mvc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "trade_live_feed_copy")
public class Trade {

	@Id
	private int tradeId;
	private String security;
	private String broker;
	private double tradePrice;
	private String instrumentType;
	@Temporal(TemporalType.DATE)
	private Date tradeDate;
	@Temporal(TemporalType.TIME)
	private Date tradeTime;
	private String currency;

	public Trade() {
		super();
	}

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	public double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public String getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + "security=" + security + ", tradePrice=" + tradePrice + ", instrumentType="
				+ instrumentType + ", tradeDate=" + tradeDate + ", tradeTime=" + tradeTime + ", currency=" + currency
				+ "]";
	}

}
