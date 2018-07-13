package com.iris.mvc.repository;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iris.mvc.model.Trade;

@Repository
public interface TradeRepository extends CrudRepository<Trade, Serializable> {

	public Trade findBySecurityAndTradeDateAndTradeTime(String security, Date tradeDate, Time tradeTime);

}
