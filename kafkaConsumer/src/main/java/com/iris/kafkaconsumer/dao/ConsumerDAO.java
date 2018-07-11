package com.iris.kafkaconsumer.dao;

import com.iris.kafkaconsumer.model.Trade;

public interface ConsumerDAO {

	void insertLiveFeed(Trade trade);

}
