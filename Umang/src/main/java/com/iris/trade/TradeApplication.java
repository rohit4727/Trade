package com.iris.trade;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * 
 * @author pushpendra.singh
 *
 */
@SpringBootApplication
public class TradeApplication {
	
	@Bean
	 public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
	     return jacksonObjectMapperBuilder -> 
	         jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
	 }

	public static void main(String[] args) {
		SpringApplication.run(TradeApplication.class, args);
	}
}
