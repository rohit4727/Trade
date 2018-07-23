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
		BestPriceRes bestPrice = null;
		try {
			URI uri = new URI(PropertiesUtil.get(LIVE_TRADE_URL) + security + "/" + tradeDate + "/" + tradeTime);
			ResponseEntity<BestPriceRes> result = restTemplate.<BestPriceRes>getForEntity(uri, BestPriceRes.class);
			bestPrice = result.getBody();
		} catch (URISyntaxException e) {
			log.error(e.getMessage());
		}
		return bestPrice != null ? bestPrice.getValue() : 0;
	}

	private static class BestPriceRes {
		private double value;
		private String message;

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
}
