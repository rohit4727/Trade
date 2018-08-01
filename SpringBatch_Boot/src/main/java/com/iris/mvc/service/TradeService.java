package com.iris.mvc.service;

import com.iris.batch.model.TradeBase;

/**
 * Service Layer: TradeService
 *
 * @author Saurabh Gupta
 */
public interface TradeService {
	public double findBestPrice(TradeBase trade);
}
