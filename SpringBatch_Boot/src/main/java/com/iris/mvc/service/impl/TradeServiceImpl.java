package com.iris.mvc.service.impl;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.mvc.dao.TradeDAO;
import com.iris.mvc.model.Trade;
import com.iris.mvc.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	TradeDAO tradeDAO;

	@Override
	public Trade findTrade(String security, Date tradeDate, Time tradeTime) {
		return tradeDAO.findTrade(security, tradeDate, tradeTime);
	}
}
