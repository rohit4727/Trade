package com.iris.kafkaconsumer.dao;

import java.util.Date;
import java.util.List;

import com.iris.kafkaconsumer.model.Trade;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
public interface TradeDAO {
	/*
	 * @return get List of 10 trade according to given security
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
	 * @return get unique Security List
	 */
	public List<String> getSeurityList();
}
