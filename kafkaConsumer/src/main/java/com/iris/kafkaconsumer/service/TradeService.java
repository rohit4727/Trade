package com.iris.kafkaconsumer.service;

import java.util.Date;
import java.util.List;

import com.iris.kafkaconsumer.model.Trade;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
public interface TradeService {

	/*
	 * @return List of 10 trade according to given security
	 */
	public List<Trade> getTradeListBySecurity(String security);

	/**
	 * @param security
	 * @param tradeDate
	 * @param tradeTime
	 * @return Trade
	 */
	public Trade findTrade(String security, Date tradeDate, Date tradeTime);

	/**
	 * @return get Security List
	 */
	public List<String> getSeurityList();
}
