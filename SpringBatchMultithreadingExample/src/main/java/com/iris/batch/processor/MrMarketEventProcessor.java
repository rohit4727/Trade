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

	@Override
	public T process(final T trade) throws Exception {

		return trade;
	}

}
