package com.iris.kafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
@SpringBootApplication
@EnableCaching
public class KafkaConsumerApplication{

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);

	}
}
