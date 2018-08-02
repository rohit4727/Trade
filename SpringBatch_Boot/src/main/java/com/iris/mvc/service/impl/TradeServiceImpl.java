package com.iris.mvc.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private static Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();

	static {
		createSecurityMap();
	}

	@Override
	public double findBestPrice(TradeBase trade) {
		Double searchedTrade = searchTrade(trade);

		if (searchedTrade != null) {
			return searchedTrade;
		} else {
			log.error("Best value not found for trade id=" + trade.getTradeId());
			return 0f;
		}

	}

	@SuppressWarnings("rawtypes")
	private Double searchTrade(TradeBase trade) {
		Map map2 = map.get(trade.getSecurity());
		if (map2 != null) {
			Object tradeRes = map2.get(trade.getTradeDate() + " " + trade.getTradeTime());
			if (tradeRes != null) {
				return Double.parseDouble(tradeRes.toString());
			}
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void createSecurityMap() {
		List kafkaTradeList = TradeServiceImpl.getTradeLiveData();

		for (Map tradeData : (List<Map>) kafkaTradeList) {

			Map securityMap = TradeServiceImpl.map.get(tradeData.get("security"));
			if (securityMap == null) {
				securityMap = new HashMap<>();
				map.put(tradeData.get("security").toString(), securityMap);
			}
			securityMap.put(tradeData.get("tradeString") + " " + tradeData.get("tradeTime"),
					tradeData.get("tradePrice").toString());

		}
	}

	@SuppressWarnings("rawtypes")
	private static synchronized List getTradeLiveData() {
		RestTemplate restTemplate = new RestTemplate();
		List result = null;

		try {
			URI uri = new URI(PropertiesUtil.get(LIVE_TRADE_URL));
			ResponseEntity<? extends ArrayList> responseEntity = restTemplate.getForEntity(uri,
					(Class<? extends ArrayList>) ArrayList.class);
			result = responseEntity.getBody();

			return result;
		} catch (URISyntaxException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
