package com.iris.kafkaconsumer.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iris.kafkaconsumer.model.Trade;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
public interface TradeRepository extends CrudRepository<Trade, Integer> {

	/**
	 * 
	 * @param security
	 * @return List of 10 latest trade according to given security
	 */
	@Query(nativeQuery = true, value = "SELECT trade_id,broker,currency,instrument_type,security,trade_date,trade_price,trade_time,direction "
			+ "FROM trade_live_feed where security=:security order by  trade_time desc limit 10")
	public List<Trade> getTradesBySecurity(@Param("security") String security);

	/**
	 * @param security
	 * @param tradeDate
	 * @param tradeTime
	 * @param toTime 
	 * @return trade as per above parameters
	 */
	@Query(nativeQuery = true, value = "SELECT trade_id,broker,currency,instrument_type,security,trade_date,trade_price,trade_time,direction "
			+"FROM trade_live_feed where security=:security and trade_date=:tradeDate and trade_time BETWEEN :fromTime and :toTime")
	public List<Trade> findBySecurityByTradeDateAndTradeTimeDuration(@Param("security") String security, @Param("tradeDate") Date tradeDate, @Param("fromTime") Date fromTime, @Param("toTime") Date toTime);

	/**
	 * @return to get Security List
	 */
	@Query(nativeQuery = true, value = "SELECT distinct security " + "FROM trade_live_feed")
	public List<String> getSeurityList();

	/**
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT trade_id,broker,currency,instrument_type,security,trade_date,trade_price,trade_time,direction "
			+ "FROM trade_live_feed order by  trade_time desc limit 10")
	public List<Trade> getTradesBySecurity();

}
