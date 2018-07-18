package com.iris.mvc.dao;

import java.sql.Date;
import java.sql.Time;

import com.iris.mvc.model.Trade;

/**
 * DAO Layer: TradeDAO
 *
 * @author Saurabh Gupta
 */
public interface TradeDAO {

	public Trade findTrade(String security, Date tradeDate, Time tradeTime);

}
