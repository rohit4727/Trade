package com.iris.mvc.service.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	private static final String LIVE_TRADE_URL = "live_trade_url";

	@Override
	public double findBestPrice(String security, String tradeDate, String tradeTime) {
		RestTemplate restTemplate = new RestTemplate();
		double bestPrice = 0f;
		try {
			URI uri = new URI(PropertiesUtil.get(LIVE_TRADE_URL) + security + "/" + tradeDate + "/" + tradeTime);
			ResponseEntity<Double> result = restTemplate.<Double>getForEntity(uri, Double.class);
			bestPrice = result.getBody();
		} catch (URISyntaxException e) {
			log.error(e.getMessage());
		}
		return bestPrice;
	}
}
