package com.iris.kafkaconsumer.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iris.kafkaconsumer.dao.TradeDAO;
import com.iris.kafkaconsumer.model.Trade;
import com.iris.kafkaconsumer.service.TradeService;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeDAO tradeDAO;

	/*
	 * @return List of 10 trade according to given security
	 */
	@Override
	// @Cacheable(value = IConstants.CACHE_NAME, key = "#security", unless =
	// "#result == null")
	public List<Trade> getTradeListBySecurity(String security) {

		return tradeDAO.getTradeListBySecurity(security);
	}

	/**
	 * @param security
	 * @param tradeDate
	 * @param tradeTime
	 * @return Trade
	 */
	@Override
	public List<Trade> findTrade(String security, Date tradeDate, Date fromTime, Date toTime) {
		return tradeDAO.findTrade(security, tradeDate, fromTime, toTime);
	}

	/**
	 * @return get Security List
	 */
	@Override
	public List<String> getSeurityList() {
		return tradeDAO.getSeurityList();
	}

}
