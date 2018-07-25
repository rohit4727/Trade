package com.iris.kafkaconsumer.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.iris.kafkaconsumer.constants.IConstants;
import com.iris.kafkaconsumer.dao.ConsumerDAO;
import com.iris.kafkaconsumer.model.Trade;
import com.iris.kafkaconsumer.service.KafkaConsumerService;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

	@Autowired
	private ConsumerDAO consumerDAO;

	/**
	 * 
	 * @param list
	 *            of trade consume Trade List
	 */
	@Override
	@KafkaListener(topics = IConstants.TOPIC, groupId = IConstants.GROUP, containerFactory = IConstants.CONTAINER_FACTORY)
	public void consumeTradeList(List<Trade> trade) {
		// trade.forEach(System.out::println);
		consumerDAO.insertLiveFeed(trade);
	}
}
