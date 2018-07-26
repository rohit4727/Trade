package com.iris.kafkaconsumer.dao;

import java.util.List;

import com.iris.kafkaconsumer.model.Trade;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
public interface ConsumerDAO {

	/**
	 * 
	 * @param trade
	 *            insert trade in DB
	 */
	void insertLiveFeed(List<Trade> trade);

}
