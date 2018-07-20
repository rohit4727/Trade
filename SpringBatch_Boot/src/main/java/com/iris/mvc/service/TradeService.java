package com.iris.mvc.service;

/**
 * Service Layer: TradeService
 *
 * @author Saurabh Gupta
 */
public interface TradeService {
	public double findBestPrice(String security, String tradeDate, String tradeTime);
}
