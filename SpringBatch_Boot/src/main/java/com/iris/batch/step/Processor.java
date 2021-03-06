package com.iris.batch.step;

import java.sql.Time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.iris.batch.model.TradeBase;
import com.iris.mvc.service.impl.TradeServiceImpl;

/**
 * Class for calculating deviation of trade price(Spring batch processing)
 * 
 * @author Saurabh Gupta
 */
public class Processor<T extends TradeBase> implements ItemProcessor<T, T> {

	private static final Logger log = LoggerFactory.getLogger(Processor.class);

	private TradeServiceImpl tradeService;

	@Autowired
	public void setTradeService(TradeServiceImpl tradeService) {
		this.tradeService = tradeService;
	}

	private Long jobId;

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TradeBase process(final TradeBase trade) throws Exception {
		if (trade == null) {
			return null;
		}
		log.info("processed trade with id " + trade.getTradeId());

		double bestPrice = tradeService.findBestPrice(trade);

		trade.setDeviation(trade.getTradePrice() - bestPrice);

		if (this.jobId != null && jobId > 0) {
			trade.setJobId(jobId);
		}

		return trade;
	}

}
