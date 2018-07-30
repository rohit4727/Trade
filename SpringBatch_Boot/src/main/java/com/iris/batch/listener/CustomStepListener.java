package com.iris.batch.listener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.web.client.RestTemplate;

import com.iris.batch.util.ETLConstants;
import com.iris.batch.util.ErrorMsg;
import com.iris.batch.util.LogMsg;
import com.iris.batch.util.PropertiesUtil;
import com.iris.mvc.model.JobProgressData;

/*
 * Step listener class for the lifecycle of a step
 * 
 * @author Rohit Elayathu
 */
public class CustomStepListener implements StepExecutionListener {

	private static final Logger log = LoggerFactory.getLogger(CustomStepListener.class);
	
	private DataSource dataSource;

	private Long jobId;
	
	private PropertiesUtil props;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public CustomStepListener(DataSource dataSource, PropertiesUtil props) {
		this.dataSource = dataSource;
		this.props = props;
	}

	/*
	 * This method stores final status of job progress in database
	 */
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {

		JobProgressData jobProgressData = new JobProgressData();
		jobProgressData.setJobId(getJobId());
		jobProgressData.setWriterLineCount(stepExecution.getWriteCount());

		if (stepExecution.getStatus() == BatchStatus.COMPLETED) {

			jobProgressData.setStatus(ETLConstants.JOB_COMPLETED);

		} else if (stepExecution.getStatus() == BatchStatus.FAILED) {

			jobProgressData.setStatus(ETLConstants.JOB_FAILED);

		}

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(props.getJobProgressServiceUrl(), jobProgressData, String.class);

		log.info(LogMsg.CUSTOMER_STEP_LISTNER_AFTER_STEP_SUCCESS + result);

		return stepExecution.getExitStatus();
	}

	/*
	 * This method stores total line count in DB before step execution starts
	 */
	@Override
	public void beforeStep(StepExecution arg0) {

		LineNumberReader reader;
		int totalLineCount = 0;

		try {
			reader = new LineNumberReader(new FileReader(props.getTradeFileName()));
			reader.skip(Integer.MAX_VALUE);

			totalLineCount = reader.getLineNumber() - props.getLinesToSkip();

			JobProgressData jobProgressData = new JobProgressData();
			jobProgressData.setJobId(getJobId());
			jobProgressData.setTotalLineCount(totalLineCount);
			jobProgressData.setStatus(ETLConstants.JOB_RUNNING);

			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.postForObject(props.getJobProgressServiceUrl(), jobProgressData, String.class);

			log.info(LogMsg.CUSTOMER_STEP_LISTNER_BEFORE_STEP_SUCCESS + result);

		} catch (FileNotFoundException e) {
			log.error(ErrorMsg.CUSTOM_STEP_LISTNER_FILE_NO_FOUND, e);
		}

		catch (IOException e) {
			log.error(ErrorMsg.CUSTOM_STEP_LISTNER_IO_EXCEPTION, e);
		}

	}

}
