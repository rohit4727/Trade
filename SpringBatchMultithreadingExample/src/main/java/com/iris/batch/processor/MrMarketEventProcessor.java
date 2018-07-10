package com.iris.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.iris.batch.model.MrMarketEvent;
import com.iris.batch.model.Trade;

/**
 * The Class FxMarketEventProcessor.
 * 
 * @author Satyveer
 */
public class MrMarketEventProcessor implements ItemProcessor<MrMarketEvent, Trade> {

	private static final Logger log = LoggerFactory.getLogger(MrMarketEventProcessor.class);

	@Override
	public Trade process(final MrMarketEvent mrMarketEvent) throws Exception {
		
		final long tradeid = Long.valueOf(mrMarketEvent.getTradeid());
		final double tradePrice = Double.valueOf(mrMarketEvent.getTradePrice());
		final String security = mrMarketEvent.getSecurity();
		final String instrumentType = mrMarketEvent.getInstrumentType();
		final String counterParty = mrMarketEvent.getCounterParty();
		final String tradeSize = mrMarketEvent.getTradeSize();
		final String tradeDate = mrMarketEvent.getTradeDate();
		final String tradeTime = mrMarketEvent.getTradeTime();
		final String currency = mrMarketEvent.getCurrency();
		final String broker = mrMarketEvent.getBroker();
		final String direction = mrMarketEvent.getDirection();
		final String tradeAlias = mrMarketEvent.getTradeAlias();
		final String contoller = mrMarketEvent.getContoller();
		final String clearingSystem = mrMarketEvent.getClearingSystem();
		final String clearinghous = mrMarketEvent.getClearinghous();
		final double tradeDeviation = 0.0;
		final Trade trade = new Trade(tradeid, tradePrice, security, instrumentType, counterParty, tradeSize, tradeDate, tradeTime, 
				currency, broker, direction, tradeAlias, contoller, clearingSystem, clearinghous, tradeDeviation);

		log.trace("Converting (" + mrMarketEvent + ") into (" + trade + ")");
        //System.out.println("Converting (" + fxMarketEvent + ") into (" + trade + ")");
		return trade;
	}

}
