package com.iris.kafkaproducer.service;

import org.springframework.kafka.core.KafkaTemplate;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 *
 */
public interface KafkaService {
	/**
	 * 
	 * produce Trade on Kafka cluster
	 */
	public String produceTrade();
}
