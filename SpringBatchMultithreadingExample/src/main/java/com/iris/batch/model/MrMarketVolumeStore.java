package com.iris.batch.model;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * The Class FxMarketPricesStore.
 *
 * @author Satyveer
 */
public class MrMarketVolumeStore {
	
	private ConcurrentMap<String, StockVolume> stockPrices = new ConcurrentHashMap<String, StockVolume>();

	public boolean containsKey(Object key) {
		return stockPrices.containsKey(key);
	}

	public StockVolume put(String key, StockVolume value) {
		return stockPrices.put(key, value);
	}

	public Collection<StockVolume> values() {
		return stockPrices.values();
	}

	public StockVolume get(Object key) {
		return stockPrices.get(key);
	}

}
