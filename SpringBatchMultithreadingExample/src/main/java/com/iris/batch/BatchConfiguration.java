package com.iris.batch;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.iris.batch.listener.JobCompletionNotificationListener;
import com.iris.batch.model.TradeBase;
import com.iris.batch.processor.MrMarketEventProcessor;
import com.iris.batch.reader.MrMarketEventReader;
import com.iris.batch.util.ETLConstants;
import com.iris.batch.util.ErrorMsg;
import com.iris.batch.util.PropertiesUtil;
import com.iris.batch.writer.TradeWriter;

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

	// FxMarketEventReader (Reader)
	@Bean
	public MrMarketEventReader<? extends TradeBase> mrMarketEventReader() {
		return new MrMarketEventReader(ETLConstants.fileName);
	}

	// FxMarketEventProcessor (Processor)
	@Bean
	public ItemProcessor<? super TradeBase, ? extends TradeBase> mrMarketEventProcessor() {
		return new MrMarketEventProcessor();
	}

	// StockVolumeAggregator (Writer)
	@Bean
	public JdbcBatchItemWriter<? super TradeBase> stockVolumeWriter(DataSource dataSource) {
		return new TradeWriter(dataSource);
	}

	// JobCompletionNotificationListener (File loader)
	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionNotificationListener();
	}

	// Configure job step
	@Bean
	public Job fxMarketPricesETLJob(Step step1) {
		return jobBuilderFactory.get(ETLConstants.jobName).incrementer(new RunIdIncrementer()).listener(listener())
				.flow(step1).end().build();
	}

	@Bean
	public TaskExecutor taskExecutor() {

		int concurrencyInt = Integer.MAX_VALUE;

		try {
			String concurrency = PropertiesUtil.get("concurrency_limit");
			if (concurrency == null || concurrency.isEmpty()) {
				log.error(ErrorMsg.concurrencyLimitNotFound);
				throw new RuntimeException();
			}

			concurrencyInt = Integer.parseInt(concurrency);

		} catch (NumberFormatException e) {
			log.error(ErrorMsg.concurrencyLimitNotInt);
		}

		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("Thread-");
		asyncTaskExecutor.setConcurrencyLimit(concurrencyInt);
		return asyncTaskExecutor;
	}

	@Bean
	public Step step1(ItemWriter<? super TradeBase> writer) {

		int chunkSizeInt = Integer.MAX_VALUE;

		try {
			String chuntSize = PropertiesUtil.get("chunk_size");
			if (chuntSize == null || chuntSize.isEmpty()) {
				log.error(ErrorMsg.chunkSizeNotFound);
				throw new RuntimeException();
			}

			chunkSizeInt = Integer.parseInt(chuntSize);

		} catch (NumberFormatException e) {
			log.error(ErrorMsg.chunkNotInt);
		}
		return stepBuilderFactory.get(ETLConstants.stepName).<TradeBase, TradeBase>chunk(chunkSizeInt)
				.reader(mrMarketEventReader()).processor(mrMarketEventProcessor()).writer(writer)
				.taskExecutor(taskExecutor()).build();
	}

}
