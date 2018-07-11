package com.iris.kafkaconsumer.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.iris.kafkaconsumer.model.Trade;

public interface TradeRepository extends CrudRepository<Trade, Serializable>{

}
