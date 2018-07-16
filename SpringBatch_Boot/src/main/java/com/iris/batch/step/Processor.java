package com.iris.batch.step;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.iris.batch.model.TradeBase;
import com.iris.mvc.model.Trade;
import com.iris.mvc.service.impl.TradeServiceImpl;

/**
 * The Class FxMarketEventProcessor.
 * 
 * @author Satyveer
 */
public class Processor<T extends TradeBase> implements ItemProcessor<T, T> {

	private static final Logger log = LoggerFactory.getLogger(Processor.class);

	@Autowired
	TradeServiceImpl tradeService;

	private Long jobId;

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TradeBase process(final TradeBase trade) throws Exception {
		log.info("processed trade with id " + trade.getTradeId());

		Trade BestPriceTrade = tradeService.findTrade(trade.getSecurity(),
				new Date((new SimpleDateFormat("dd-MM-yyyy").parse(trade.getTradeDate()).getTime())),
				Time.valueOf(trade.getTradeTime()));

		if (BestPriceTrade != null) {
			trade.setDeviation(trade.getTradePrice() - BestPriceTrade.getTradePrice());
		}

		if (this.jobId != null && jobId > 0) {
			trade.setJobId(jobId);
		}

		return trade;
	}

}
