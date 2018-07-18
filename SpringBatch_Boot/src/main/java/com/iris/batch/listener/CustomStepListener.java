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
import org.springframework.jdbc.core.JdbcTemplate;

import com.iris.batch.util.ETLConstants;
import com.iris.batch.util.ErrorMsg;
import com.iris.batch.util.LogMsg;
import com.iris.batch.util.PropertiesUtil;

/*
 * Step listener class for the lifecycle of a step
 * 
 * @author Rohit Elayathu
 */
public class CustomStepListener implements StepExecutionListener {

	private static final Logger log = LoggerFactory.getLogger(CustomStepListener.class);

	private final String INSERT_QUERY = "insert into JOB_PROGRESS_DATA(job_Id,total_line_count,status) values (?,?,?)";
	private final String UPDATE_QUERY = "update JOB_PROGRESS_DATA set writer_line_count = ?, status = ? where job_id = ?";

	private DataSource dataSource;

	private Long jobId;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public CustomStepListener(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * This method stores final status of job progress in database
	 */
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

		if (stepExecution.getStatus() == BatchStatus.COMPLETED) {

			Object[] params = { stepExecution.getWriteCount(), ETLConstants.JOB_COMPLETED, getJobId() };

			jdbcTemplate.update(UPDATE_QUERY, params);

		} else if (stepExecution.getStatus() == BatchStatus.FAILED) {

			Object[] params = { stepExecution.getWriteCount(), ETLConstants.JOB_FAILED, getJobId() };

			jdbcTemplate.update(UPDATE_QUERY, params);

		}

		log.info(LogMsg.CUSTOMER_STEP_LISTNER_AFTER_STEP_SUCCESS + stepExecution.getStatus() + ","
				+ stepExecution.getWriteCount());

		return null;
	}

	/*
	 * This method stores total line count in DB before step execution starts
	 */
	@Override
	public void beforeStep(StepExecution arg0) {

		LineNumberReader reader;
		String filePath = null;
		int totalLineCount = 0;

		try {
			filePath = PropertiesUtil.get("file_path") + PropertiesUtil.get("trade_file_name");
			reader = new LineNumberReader(new FileReader(filePath));
			reader.skip(Integer.MAX_VALUE);

			JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

			totalLineCount = reader.getLineNumber() - ETLConstants.LINES_TO_SKIP;

			Object[] params = { getJobId(), totalLineCount, ETLConstants.JOB_RUNNING };
			jdbcTemplate.update(INSERT_QUERY, params);

			log.info(LogMsg.CUSTOMER_STEP_LISTNER_BEFORE_STEP_SUCCESS + totalLineCount);

		} catch (FileNotFoundException e) {
			log.error(ErrorMsg.CUSTOM_STEP_LISTNER_FILE_NO_FOUND, e);
		}

		catch (IOException e) {
			log.error(ErrorMsg.CUSTOM_STEP_LISTNER_IO_EXCEPTION, e);
		}

	}

}
