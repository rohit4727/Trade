package com.iris.mvc.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iris.batch.model.TradeBase;
import com.iris.batch.util.PropertiesUtil;
import com.iris.mvc.service.TradeService;

/**
 * Service Layer Implementaion: TradeServiceImpl
 *
 * @author Saurabh Gupta
 */
@Service
public class TradeServiceImpl implements TradeService {

	private static final Logger log = LoggerFactory.getLogger(TradeServiceImpl.class);

	private static final String LIVE_TRADE_URL = "liveTradeUrl";

	private static Map<String, Map<String, TradeRes>> map;
	
	static {
		
	}
	
	

	@Override
	public double findBestPrice(TradeBase trade) {
		TradeRes searchedTrade = searchTrade(trade);
		if (searchedTrade == null) {
			for (TradeRes tradeRes : getBestValueBunch(trade)) {
				updateSecurityMap(map, tradeRes);
			}
			searchedTrade = searchTrade(trade);
		}
		if (searchedTrade != null) {
			return searchedTrade.tradePrice;
		} else {
			throw new RuntimeException("Best value not found for trade id=" + trade.getTradeId());
		}

		// return 100f;
		// "89.34" or "Trade price not found!"
	}

	private TradeRes searchTrade(TradeBase trade) {
		Map<String, TradeRes> map2 = map.get(trade.getSecurity());
		if (map2 != null) {
			TradeRes tradeRes = map2.get(trade.getTradeDate() + " " + trade.getTradeTime());
			if (tradeRes != null) {
				return tradeRes;
			}
		}
		return null;
	}

	private synchronized List<TradeRes> getBestValueBunch(TradeBase trade) {
		RestTemplate restTemplate = new RestTemplate();
		Res result = null;

		String fromTime = trade.getTradeTime();
		Time time = Time.valueOf(fromTime);
		time.setTime(time.getTime() + 30 * 60 * 1000);

		try {
			URI uri = new URI(PropertiesUtil.get(LIVE_TRADE_URL) + trade.getSecurity() + "/" + trade.getTradeDate()
					+ "/" + fromTime + "/" + time);
			ResponseEntity<Res> responseEntity = restTemplate.<Res>getForEntity(uri, Res.class);
			result = responseEntity.getBody();
			if (result.message != null) {
				log.error(result.message);
				throw new RuntimeException(result.message);
			} else {
				return result.tradeList;
			}
		} catch (URISyntaxException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	private void updateSecurityMap(Map<String, Map<String, TradeRes>> map, TradeRes trade) {
		if (map == null) {
			map = new ConcurrentHashMap<>();
		}
		map.put(trade.security, updateTimeMap(map.get(trade.security), trade));
	}

	private Map<String, TradeRes> updateTimeMap(Map<String, TradeRes> map, TradeRes trade) {
		if (map == null) {
			map = new ConcurrentHashMap<>();
		}
		map.put(trade.tradeDate + " " + trade.tradeTime, trade);
		return map;
	}

	private static class Res {
		private List<TradeRes> tradeList;
		private String message;
	}

	private static class TradeRes {
		private String security;
		private double tradePrice;
		private String tradeDate;
		private String tradeTime;
	}
}
