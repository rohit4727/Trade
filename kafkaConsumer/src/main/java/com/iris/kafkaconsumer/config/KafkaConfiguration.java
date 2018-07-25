package com.iris.kafkaconsumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.iris.kafkaconsumer.constants.IConstants;
import com.iris.kafkaconsumer.model.Trade;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
@EnableKafka
@Configuration
public class KafkaConfiguration {

	/**
	 * 
	 * @return default configuration of Kafka consumer factory
	 */
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IConstants.KAFKA_URL);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, IConstants.GROUP);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);
		return new DefaultKafkaConsumerFactory<>(config);
	}

	/**
	 * 
	 * @return default kafka listener factory
	 */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(consumerFactory());
		factory.setBatchListener(true);
		// factory.getContainerProperties().setBatchErrorHandler(new
		// BatchLoggingErrorHandler());
		return factory;
	}

	/**
	 * 
	 * @return customized kafka consumer factory
	 */
	@Bean
	public ConsumerFactory<String, Trade> tradeConsumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IConstants.KAFKA_URL);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, IConstants.GROUP);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(Trade.class));
	}

	/**
	 * 
	 * @return customized kafka listener factory
	 */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Trade> tradeKafkaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Trade> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(tradeConsumerFactory());
		factory.setBatchListener(true);
		// factory.getContainerProperties().setBatchErrorHandler(new
		// BatchLoggingErrorHandler());
		return factory;
	}

}
