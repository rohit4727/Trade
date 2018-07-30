package com.iris.kafkaconsumer.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.iris.kafkaconsumer.constants.IConstants;
import com.iris.kafkaconsumer.model.Trade;
import com.iris.kafkaconsumer.service.TradeService;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */

@RestController
public class TradeController {

	@Autowired
	private TradeService tradeService;

	/**
	 * 
	 * @param security
	 * @return list of trades
	 */
	@GetMapping(value = IConstants.GET_SECURITY_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Trade> getTradeListBySecurity(@PathVariable String security) {
		return tradeService.getTradeListBySecurity(security);
	}

	/**
	 * 
	 * @param security
	 * @param tradeDate
	 * @param tradeTime
	 * @return trade prize in string format
	 * @throws ParseException
	 */
	@GetMapping(value = IConstants.FIND_TRADE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Trade> findTrade(@PathVariable String security, @PathVariable String tradeDate,
			@PathVariable String fromTime, @PathVariable String toTime) throws ParseException {
		List<Trade> trades = tradeService.findTrade(security, new SimpleDateFormat(IConstants.DATE_FORMATE).parse(tradeDate),
				new SimpleDateFormat(IConstants.TIME_FORMATE).parse(fromTime),  new SimpleDateFormat(IConstants.TIME_FORMATE).parse(toTime));
		return trades;// != null ? "{\"value\" : " + trade.getTradePrice() + "}" : "{\"message\" : \"Trade not Found!\"}";
	}

	/**
	 * 
	 * @return unique value of different securities
	 */
	@GetMapping(value = IConstants.GET_SECURITY, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getSeurityList() {
		return tradeService.getSeurityList();
	}

	/*
	 * @GetMapping("/cache") public int getCachedData() { Trade trade = null;
	 * 
	 * Map<Integer, Trade> map = hazelcastInstance.getMap("tradeLists");
	 * 
	 * Collection<Trade> colTrades = map.values(); for (Trade tra : colTrades) {
	 * System.out.println("----" + tra); }
	 * 
	 * return (int) map.entrySet().stream().count();
	 * 
	 * }
	 */
}
