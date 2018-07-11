package com.iris.kafkaconsumer.daoImpl;

import org.springframework.stereotype.Repository;

import com.iris.kafkaconsumer.dao.ConsumerDAO;
import com.iris.kafkaconsumer.model.Trade;

@Repository
public class ConsumerDAOImpl implements ConsumerDAO {

	
	@Override
	public void insertLiveFeed(Trade trade) {
		System.out.println("Consumed JSON Message: " + trade);
	}

}
