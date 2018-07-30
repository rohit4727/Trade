package com.iris.test.unit;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.iris.batch.model.TradeBase;
import com.iris.batch.step.Processor;
import com.iris.mvc.service.impl.TradeServiceImpl;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProcessorTest {

	private static final Logger log = LoggerFactory.getLogger(Reader.class);

	@Autowired
	@Qualifier("itemProcessorTest")
	private ItemProcessor<TradeBase, TradeBase> processor;

	// @Autowired
	// private ItemReader<? extends TradeBase> reader;

	@Mock
	private TradeServiceImpl tradeService;

	public List<? extends TradeBase> tradeList() {

		List<? extends TradeBase> tradeList = new ArrayList<TradeBase>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{
				add(new TradeBase() {
					{
						setTradePrice(0d);
						setSecurity(anyString());
						setTradeDate(anyString());
						setTradeTime(anyString());
					}
				});
			}

		};
		return tradeList;
	}

	int deviationCounted = 0;

	@Test
	public void testProcessor() {
		when(this.tradeService.findBestPrice(anyString(), anyString(), anyString(), anyString())).thenReturn(100.02);

		((Processor<TradeBase>) processor).setTradeService(tradeService);
		((Processor<TradeBase>) processor).setJobId(1L);

		System.out.println(processor.getClass().getName());
		Consumer<TradeBase> action = (TradeBase trade) -> {
			try {
				trade = processor.process(trade);
			} catch (Exception e) {
				log.error("Error", e);
			}
			if (trade != null && trade.getDeviation() != 0) {
				deviationCounted++;
			}
		};
		tradeList().forEach(action);
		assertTrue(deviationCounted > 0);
	}
}
