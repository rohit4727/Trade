package com.iris.batch.model;

/**
 * The Class Trade.
 * 
 * @author Satyveer
 */
public class Trade1 {

    private long tradeid;
	private double tradePrice;
    private String security;
    private String instrumentType;
    private String counterParty;
    private String tradeSize;
    private String tradeDate;
    private String tradeTime;
    private String currency;
    private String broker;
    private String direction;
    private String tradeAlias;
    private String contoller;
    private String clearingSystem;
    private String clearinghous;
	private double tradeDeviation;
    

	public Trade1(long tradeid, double tradePrice, String security, String instrumentType, 
			String counterParty, String tradeSize, String tradeDate, String tradeTime, String currency, 
			String broker, String direction, String tradeAlias, String contoller, String clearingSystem, String clearinghous, double tradeDeviation) {
		super();
		this.tradeid = tradeid;
		this.tradePrice = tradePrice;
		this.security = security;
		this.instrumentType = instrumentType;
		this.counterParty = counterParty;
		this.tradeSize = tradeSize;
		this.tradeDate = tradeDate;
		this.tradeTime = tradeTime;
		this.currency = currency;
		this.broker = broker;
		this.direction = direction;
		this.tradeAlias = tradeAlias;
		this.contoller = contoller;
		this.clearingSystem = clearingSystem;
		this.clearinghous = clearinghous;
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

	public String getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}

	public String getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}

	public String getTradeSize() {
		return tradeSize;
	}

	public void setTradeSize(String tradeSize) {
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getTradeAlias() {
		return tradeAlias;
	}

	public void setTradeAlias(String tradeAlias) {
		this.tradeAlias = tradeAlias;
	}

	public String getContoller() {
		return contoller;
	}

	public void setContoller(String contoller) {
		this.contoller = contoller;
	}

	public String getClearingSystem() {
		return clearingSystem;
	}

	public void setClearingSystem(String clearingSystem) {
		this.clearingSystem = clearingSystem;
	}

	public String getClearinghous() {
		return clearinghous;
	}

	public void setClearinghous(String clearinghous) {
		this.clearinghous = clearinghous;
	}

    public double getTradeDeviation() {
		return tradeDeviation;
	}

	public void setTradeDeviation(double tradeDeviation) {
		this.tradeDeviation = tradeDeviation;
	}

	@Override
	public String toString() {
		return "MrMarketEvent [tradeid=" + tradeid + ", tradePrice=" + tradePrice + ", security=" + security + ", instrumentType=" + instrumentType +", counterParty=" + counterParty + ", tradeSize=" + tradeSize + ", tradeDate=" + tradeDate +", currency=" + currency + ", broker=" + broker + ", direction=" + direction +", tradeAlias=" + tradeAlias + ", contoller=" + contoller + ", clearingSystem=" + clearingSystem + ", clearinghous=" + clearinghous + ", tradeDeviation"+tradeDeviation+"]";
	}
}

