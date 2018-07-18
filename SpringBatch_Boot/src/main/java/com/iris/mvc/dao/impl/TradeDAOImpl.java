package com.iris.mvc.dao.impl;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iris.mvc.dao.TradeDAO;
import com.iris.mvc.model.Trade;
import com.iris.mvc.repository.TradeRepository;

/**
 * DAO Layer Implementation: TradeDAOImpl
 *
 * @author Saurabh Gupta
 */
@Repository
public class TradeDAOImpl implements TradeDAO {

	@Autowired
	private TradeRepository tradeRepository;

	@Override
	public Trade findTrade(String security, Date tradeDate, Time tradeTime) {
		return tradeRepository.findBySecurityAndTradeDateAndTradeTime(security, tradeDate, tradeTime);
	}
}
