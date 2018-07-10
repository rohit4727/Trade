package com.iris.batch.listener;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.iris.batch.model.MrMarketVolumeStore;
import com.iris.batch.model.StockVolume;

/**
 * The Class JobCompletionNotificationListener
 *
 * @author Satyveer
 */
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private static final String HEADER = "stock,volume";

	private static final String LINE_DILM = ",";

	@Autowired
	private MrMarketVolumeStore mrMarketVolumeStore;

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println(Thread.currentThread().getName()+"......Batch has been successfully completed................");
		/*	log.trace("Loading the results into file");
			Path path = Paths.get("volume.csv");
			try (BufferedWriter fileWriter = Files.newBufferedWriter(path)) {
				fileWriter.write(HEADER);
				fileWriter.newLine();
				for (StockVolume pd : mrMarketVolumeStore.values()) {
					fileWriter.write(new StringBuilder().append(pd.getStock())
							.append(LINE_DILM).append(pd.getVolume()).toString());
					fileWriter.newLine();
				}
			} catch (Exception e) {
				log.error("Fetal error: error occurred while writing {} file", path.getFileName());
			} */
		} 
	}
}
