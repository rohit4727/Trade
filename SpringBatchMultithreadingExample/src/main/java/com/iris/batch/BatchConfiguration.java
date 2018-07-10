package com.iris.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.util.Assert;

import com.iris.batch.listener.JobCompletionNotificationListener;
import com.iris.batch.model.TradeBase;
import com.iris.batch.model.MrMarketVolumeStore;
import com.iris.batch.model.Trade1;
import com.iris.batch.processor.MrMarketEventProcessor;
import com.iris.batch.reader.MrMarketEventReader;
import com.iris.batch.util.PropertiesUtil;
import com.iris.batch.writer.StockVolumeWriter;

/**
 * The Class BatchConfiguration.
 * 
 * @author Satyveer
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public MrMarketVolumeStore fxMarketPricesStore() {
		return new MrMarketVolumeStore();
	}

	// FxMarketEventReader (Reader)
	@Bean
	public MrMarketEventReader mrMarketEventReader() {
		return new MrMarketEventReader();
	}

	// FxMarketEventProcessor (Processor)
	@Bean
	public MrMarketEventProcessor mrMarketEventProcessor() {
		return new MrMarketEventProcessor();
	}

	// StockVolumeAggregator (Writer)
	@Bean
	public StockVolumeWriter stockVolumeWriter() {
		return new StockVolumeWriter();
	}

	// JobCompletionNotificationListener (File loader)
	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionNotificationListener();
	}

	// Configure job step
	@Bean
	public Job fxMarketPricesETLJob() {
		return jobBuilderFactory.get("CITI Market Risk").incrementer(new RunIdIncrementer()).listener(listener())
				.flow(etlStep()).end().build();
	}

	@Bean
	public TaskExecutor taskExecutor() {

		int concurrencyInt = Integer.MAX_VALUE;

		try {
			String concurrency = PropertiesUtil.get("concurrency_limit");
			Assert.isNull(concurrency, "concurrency_limit not found in application.properties file");

			concurrencyInt = Integer.parseInt(concurrency);

		} catch (NumberFormatException e) {
			log.error("Chunk size is not integer");
		}
		
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("Thread-");
		asyncTaskExecutor.setConcurrencyLimit(concurrencyInt);
		return asyncTaskExecutor;
	}

	@Bean
	public Step etlStep() {
		
		int chunkSizeInt = Integer.MAX_VALUE;
		
		try {
			String chuntSize = PropertiesUtil.get("chunk_size");
			Assert.isNull(chuntSize, "chunk_size not found in application.properties file");

			chunkSizeInt = Integer.parseInt(chuntSize);

		} catch (NumberFormatException e) {
			log.error("Chunk size is not integer");
		}
		return stepBuilderFactory.get("Extract -> Transform -> Aggregate -> Load")
				.<TradeBase, Trade1>chunk(chunkSizeInt).reader(mrMarketEventReader())
				.processor(mrMarketEventProcessor()).writer(stockVolumeWriter()).taskExecutor(taskExecutor()).build();
	}

}
