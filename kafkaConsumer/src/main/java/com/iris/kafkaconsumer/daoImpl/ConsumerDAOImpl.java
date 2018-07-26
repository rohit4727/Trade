package com.iris.kafkaconsumer.daoImpl;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hazelcast.core.HazelcastInstance;
import com.iris.kafkaconsumer.constants.IConstants;
import com.iris.kafkaconsumer.dao.ConsumerDAO;
import com.iris.kafkaconsumer.model.Trade;
import com.iris.kafkaconsumer.repository.TradeRepository;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
@Repository
public class ConsumerDAOImpl implements ConsumerDAO {

	static Logger logger = Logger.getLogger(ConsumerDAOImpl.class.getName());

	@Autowired
	private HazelcastInstance hazelcastInstance;

	@Autowired
	private TradeRepository tradeRepo;

	/**
	 * insert list of trades in DB as well as in cache
	 */
	@Override
	public void insertLiveFeed(List<Trade> trade) {

		try {
			cachedTrade(trade);// insert in cache
			tradeRepo.saveAll(trade);// insert in DB
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}

	}

	public void cachedTrade(List<Trade> trade) {
		Map<Integer, Trade> map = hazelcastInstance.getMap(IConstants.GLOBAL_CACHE);
		trade.stream().filter(t -> {
			map.put(t.getTradeId(), t);
			return true;
		}).count();
	}

}
