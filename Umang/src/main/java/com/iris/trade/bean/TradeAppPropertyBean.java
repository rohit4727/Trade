package com.iris.trade.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:trade_app.properties")
@Component
public class TradeAppPropertyBean {
	
    @Value("${scheduleJobRestAPI}")
	public String scheduleJobRestAPI;
    
    @Value("${runJobRestAPI}")
	public String runJobRestAPI;

}
