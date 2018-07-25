package com.iris.kafkaconsumer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
@Entity
@Table(name = "TRADE_LIVE_FEED")
public class Trade implements Serializable {

	private static final long serialVersionUID = -3193371204566316446L;

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
	private String direction;

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
		return "Trade [tradeId=" + tradeId + ", security=" + security + ", tradePrice=" + tradePrice
				+ ", instrumentType=" + instrumentType + ", tradeDate=" + tradeDate + ", tradeTime=" + tradeTime
				+ ", currency=" + currency + ", direction=" + direction + "]";
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
