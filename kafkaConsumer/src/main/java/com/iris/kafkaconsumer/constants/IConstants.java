package com.iris.kafkaconsumer.constants;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 * 
 */
public interface IConstants {

	public static final String TOPIC = "trade_feed";
	public static final String GROUP = "trade_json";
	public static final String CONTAINER_FACTORY = "tradeKafkaListenerFactory";
	public static final String KAFKA_URL = "127.0.0.1:9092";
	public static final String GET_SECURITY_URL = "/Trade/{security}";
	public static final String HAZELCAST_INSTANCE = "hazelcast-instance";
	public static final String CACHE_NAME = "tradeList";
	public static final String GLOBAL_CACHE = "tradeLists";
	public static final String DEFAULT_SECURITY = "ALL";
	public static final String FIND_TRADE = "/Trade/{security}/{tradeDate}/{fromTime}/{toTime}";
	public static final String DATE_FORMATE = "dd-MM-yyyy";
	public static final String TIME_FORMATE = "HH:mm:ss";
	public static final String GET_SECURITY = "/Trade/getSecurityList";
}
