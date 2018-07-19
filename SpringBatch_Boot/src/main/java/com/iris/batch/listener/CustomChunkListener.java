package com.iris.batch.listener;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.iris.batch.util.ErrorMsg;
import com.iris.batch.util.LogMsg;

/*
 * Chunk listener class for the lifecycle of a chunk
 * 
 * @author Rohit Elayathu
 */
public class CustomChunkListener implements ChunkListener {

	private static final Logger log = LoggerFactory.getLogger(CustomChunkListener.class);

	private final String UPDATE_QUERY = "update JOB_PROGRESS_DATA set writer_line_count = ? where job_id = ?";

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

		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

		int writeCount = context.getStepContext().getStepExecution().getWriteCount();

		Object[] params = { writeCount, getJobId() };
		jdbcTemplate.update(UPDATE_QUERY, params);

		log.info(LogMsg.CUSTOMER_CHUNK_LISTNER_AFTER_CHUNK_SUCCESS + writeCount);
	}

	@Override
	public void afterChunkError(ChunkContext context) {

		log.error(ErrorMsg.CUSTOM_CHUNK_LISTNER_ERROR);

	}

}
