package com.iris.batch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.iris.batch.model.MrMarketVolumeStore;
import com.iris.batch.model.StockVolume;
import com.iris.batch.model.Trade;

/**
 * The Class StockVolumeAggregator.
 * 
 * @author Satyveer
 */
public class StockVolumeWriter implements ItemWriter<Trade> {

	@Autowired
	private MrMarketVolumeStore mrMarketVolumeStore;

	private static final Logger log = LoggerFactory.getLogger(StockVolumeWriter.class);

	@Override
	public void write(List<? extends Trade> trades) throws Exception {
		
		
		trades.forEach(t -> System.out.println(Thread.currentThread().getName()+" === "+t.toString()));
		
		/*	if (mrMarketVolumeStore.containsKey(t.getStock())) {
				StockVolume stockVolume = mrMarketVolumeStore.get(t.getStock());
				long newVolume = stockVolume.getVolume() + t.getShares();
				// Increment stock volume
				stockVolume.setVolume(newVolume);
			} else {
				log.trace("Adding new stock {}", t.getStock());
				mrMarketVolumeStore.put(t.getStock(),
						new StockVolume(t.getStock(), t.getShares()));
			}
		}); */
	}

}
