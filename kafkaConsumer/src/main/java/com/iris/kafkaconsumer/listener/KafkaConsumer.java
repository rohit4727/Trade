package com.iris.kafkaconsumer.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.iris.kafkaconsumer.constants.IControllerConstants;
import com.iris.kafkaconsumer.dao.ConsumerDAO;
import com.iris.kafkaconsumer.model.Trade;

@Service
public class KafkaConsumer {

	@Autowired
	ConsumerDAO consumerDAO;
	
    @KafkaListener(topics = IControllerConstants.TOPIC, group = IControllerConstants.GROUP,
            containerFactory = IControllerConstants.CONTAINER_FACTORY)
    public void consumeJson(Trade trade) {
        System.out.println("Consumed JSON Message: " + trade);
    	
    	consumerDAO.insertLiveFeed(trade);
    }
}
