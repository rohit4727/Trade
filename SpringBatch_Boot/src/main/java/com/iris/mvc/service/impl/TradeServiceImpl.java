package com.iris.mvc.service.impl;

import java.sql.Date;
import java.sql.Time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.mvc.dao.TradeDAO;
import com.iris.mvc.model.Trade;
import com.iris.mvc.service.TradeService;

/**
 * Service Layer Implementaion: TradeServiceImpl
 *
 * @author Saurabh Gupta
 */
@Service
public class TradeServiceImpl implements TradeService {

	private static final Logger log = LoggerFactory.getLogger(TradeServiceImpl.class);
			
	@Autowired
	private TradeDAO tradeDAO;

	@Override
	public Trade findTrade(String security, Date tradeDate, Time tradeTime) {
		return tradeDAO.findTrade(security, tradeDate, tradeTime);
	}
}
