package com.iris.mvc.service;

import java.sql.Date;
import java.sql.Time;

import com.iris.mvc.model.Trade;

/**
 * Service Layer: TradeService
 *
 * @author Saurabh Gupta
 */
public interface TradeService {
	public Trade findTrade(String security, Date tradeDate, Time tradeTime);
}
