package com.iris.kafkaconsumer.constants;

public interface IControllerConstants {
	
	public static final String TOPIC = "trade_feed";
	public static final String GROUP = "trade_json";
	public static final String CONTAINER_FACTORY = "tradeKafkaListenerFactory";
	public static final String KAFKA_URL = "127.0.0.1:9092";
}
