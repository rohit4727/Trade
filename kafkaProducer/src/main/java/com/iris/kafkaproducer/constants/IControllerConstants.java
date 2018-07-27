package com.iris.kafkaproducer.constants;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 *
 */
public interface IControllerConstants {

	public static final String TOPIC = "trade_feed";
	public static final String GROUP = "trade_json";
	public static final String CONTAINER_FACTORY = "tradeKafkaListenerFactory";
	public static final String KAFKA_URL = "127.0.0.1:9092";
	public static final String CSV_FILE = "C:\\Users\\rohit.chauhan\\Desktop\\SampleTradeKafka.csv";
	public static final String KAFKA_PRODUCER = "/kafkaProducer";
	public static final String SUCCESS = "Published successfully";
	public static final String FAILURE = "Unable to published Trade";
	public static final String DATE_FORMATE = "dd-MM-yyyy";
	public static final String TIME_FORMATE = "HH:mm:ss";
}
