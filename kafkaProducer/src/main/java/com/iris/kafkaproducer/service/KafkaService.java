package com.iris.kafkaproducer.service;

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
