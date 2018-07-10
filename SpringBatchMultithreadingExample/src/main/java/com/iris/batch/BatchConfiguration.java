package com.iris.batch;

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

import com.iris.batch.listener.JobCompletionNotificationListener;
import com.iris.batch.model.MrMarketEvent;
import com.iris.batch.model.MrMarketVolumeStore;
import com.iris.batch.model.Trade;
import com.iris.batch.processor.MrMarketEventProcessor;
import com.iris.batch.reader.MrMarketEventReader;
import com.iris.batch.writer.StockVolumeWriter;

/**
 * The Class BatchConfiguration.
 * 
 * @author Satyveer
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

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
		return jobBuilderFactory.get("CITI Market Risk").incrementer(new RunIdIncrementer()).listener(listener()).flow(etlStep()).end().build();
	}
	
	@Bean
	public TaskExecutor taskExecutor(){
	    SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("Thread-");
	    asyncTaskExecutor.setConcurrencyLimit(5);
	    return asyncTaskExecutor;
	}
    
	@Bean
	public Step etlStep() {
		return stepBuilderFactory.get("Extract -> Transform -> Aggregate -> Load").<MrMarketEvent, Trade> chunk(10000)
				.reader(mrMarketEventReader()).processor(mrMarketEventProcessor())
				.writer(stockVolumeWriter())
				.taskExecutor(taskExecutor()).build();
	}

}
