package com.iris.kafkaproducer.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iris.kafkaproducer.constants.IControllerConstants;
import com.iris.kafkaproducer.service.KafkaService;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 *
 */
@RestController
@RequestMapping(IControllerConstants.KAFKA_PRODUCER)
public class TradeProducerController {

	static Logger logger = Logger.getLogger(TradeProducerController.class.getName());

	@Autowired
	private KafkaService kafkaService;

	/**
	 * 
	 * Manual execution of producer URL : http://localhost:8082/kafkaProducer/
	 */
	@GetMapping("/")
	public String postTrade() {
		return kafkaService.produceTrade();
	}

	/**
	 * Self execution of producer
	 */
	@PostConstruct
	public void Init() {
		kafkaService.produceTrade();
	}
}
