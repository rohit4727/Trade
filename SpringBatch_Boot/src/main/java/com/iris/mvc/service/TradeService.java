package com.iris.mvc.service;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.mvc.model.Trade;
import com.iris.mvc.repository.TradeRepository;

@Service
public class TradeService {

	@Autowired
	TradeRepository tradeRepository;

	public Trade findTrade(String security, Date tradeDate, Time tradeTime) {
		return tradeRepository.findBySecurityAndTradeDateAndTradeTime(security, tradeDate, tradeTime);
	}
}
