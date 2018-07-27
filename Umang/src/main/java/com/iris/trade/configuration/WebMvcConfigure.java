package com.iris.trade.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer{

	@Value("${tradeApp.welcomefile.path}")
	private String welcomeFilePath;
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:" + welcomeFilePath);
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
	}

}