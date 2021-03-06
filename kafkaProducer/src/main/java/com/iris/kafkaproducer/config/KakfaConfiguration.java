package com.iris.kafkaproducer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.iris.kafkaproducer.constants.IControllerConstants;
import com.iris.kafkaproducer.model.Trade;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 *
 */
@Configuration
public class KakfaConfiguration {

	/**
	 * 
	 * @return Kafka Producer Factory
	 */
	@Bean
	public ProducerFactory<String, Trade> producerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IControllerConstants.KAFKA_URL);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(config);
	}

	/**
	 * 
	 * @return kafka Template
	 */
	@Bean
	public KafkaTemplate<String, Trade> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

}
