package com.iris.kafkaproducer.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iris.kafkaproducer.constants.IControllerConstants;
import com.iris.kafkaproducer.model.Trade;
import com.iris.kafkaproducer.service.KafkaService;
import com.iris.kafkaproducer.serviceImpl.KafkaServiceImpl;

@RestController
@RequestMapping(IControllerConstants.KAFKA_PRODUCER)
public class TradeProducerController {
	
	static Logger logger = Logger.getLogger(TradeProducerController.class.getName());

    @Autowired
    private KafkaService kafkaService; 
    
    @GetMapping("/")
    public String postTrade() {
    	return kafkaService.produceTrade();
    }
}
