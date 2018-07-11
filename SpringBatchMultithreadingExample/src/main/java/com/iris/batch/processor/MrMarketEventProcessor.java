package com.iris.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.iris.batch.model.TradeBase;

/**
 * The Class FxMarketEventProcessor.
 * 
 * @author Satyveer
 */
public class MrMarketEventProcessor<T extends TradeBase> implements ItemProcessor<T, T> {

	private static final Logger log = LoggerFactory.getLogger(MrMarketEventProcessor.class);

	@SuppressWarnings("unchecked")
	@Override
	public TradeBase process(final TradeBase trade) throws Exception {
		log.info("processed trade with id " + trade.getTradeid());
		trade.setDeviation(12);
		return trade;
	}

}
