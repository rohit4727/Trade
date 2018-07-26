package com.iris.kafkaconsumer.service;

import java.util.List;

import com.iris.kafkaconsumer.model.Trade;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
public interface KafkaConsumerService {
	/**
	 * 
	 * @param trade
	 *            consume Trade List
	 */
	public void consumeTradeList(List<Trade> trade);
}
