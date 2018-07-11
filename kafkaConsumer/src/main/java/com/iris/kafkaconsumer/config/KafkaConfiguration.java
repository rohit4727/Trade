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

import com.iris.kafkaconsumer.constants.IControllerConstants;
import com.iris.kafkaconsumer.model.Trade;

@EnableKafka
@Configuration
public class KafkaConfiguration {

	@Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IControllerConstants.KAFKA_URL);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, IControllerConstants.GROUP);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    
    @Bean
    public ConsumerFactory<String, Trade> tradeConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IControllerConstants.KAFKA_URL);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, IControllerConstants.GROUP);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(Trade.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Trade> tradeKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Trade> factory = new ConcurrentKafkaListenerContainerFactory<>();
         factory.setConsumerFactory(tradeConsumerFactory());
        return factory;
    }

}
