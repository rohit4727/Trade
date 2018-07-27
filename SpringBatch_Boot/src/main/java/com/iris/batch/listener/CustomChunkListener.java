package com.iris.batch.listener;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import com.iris.batch.util.ETLConstants;
import com.iris.batch.util.ErrorMsg;
import com.iris.batch.util.LogMsg;
import com.iris.batch.util.PropertiesUtil;
import com.iris.mvc.model.JobProgressData;

/*
 * Chunk listener class for the lifecycle of a chunk
 * 
 * @author Rohit Elayathu
 */

public class CustomChunkListener implements ChunkListener {

	private static final Logger log = LoggerFactory.getLogger(CustomChunkListener.class);
	private static final String JOB_PROGRESS_SERVICE_URL = "job_progress_service_url";

	private DataSource dataSource;

	private Long jobId;

	public CustomChunkListener(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public void beforeChunk(ChunkContext context) {
		// TODO Auto-generated method stub

	}

	/*
	 * This method stores current write count in database
	 */
	@Override
	public void afterChunk(ChunkContext context) {

		int writeCount = context.getStepContext().getStepExecution().getWriteCount();
		
		JobProgressData jobProgressData = new JobProgressData();
		jobProgressData.setJobId(getJobId());
		jobProgressData.setWriterLineCount(writeCount);
		jobProgressData.setStatus(ETLConstants.JOB_RUNNING);
		
		saveProcessedCount(jobProgressData);
	}

	@Async
	private void saveProcessedCount(JobProgressData jobProgressData) {

		String uri = PropertiesUtil.get(JOB_PROGRESS_SERVICE_URL);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(uri, jobProgressData, String.class);

		log.info(LogMsg.CUSTOMER_CHUNK_LISTNER_AFTER_CHUNK_SUCCESS + result);
	}

	@Override
	public void afterChunkError(ChunkContext context) {

		log.error(ErrorMsg.CUSTOM_CHUNK_LISTNER_ERROR);

	}

}
