package com.iris.batch.step;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.iris.batch.model.TradeBase;
import com.iris.batch.util.ErrorMsg;
import com.iris.batch.util.PropertiesUtil;

/**
 * Class for writing spring batch processed data
 * 
 * @author Saurabh
 */
public class Writer<T extends TradeBase> extends JdbcBatchItemWriter<T> {
	private static final Logger log = LoggerFactory.getLogger(Writer.class);

	public Writer(DataSource dataSource) {
		String insertionQuery = PropertiesUtil.get("insertion.query");
		if (insertionQuery == null || insertionQuery.isEmpty()) {
			log.error(ErrorMsg.INSERTION_QUERY_NOT_FOUND);
			throw new RuntimeException();
		}
		this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		this.setSql(insertionQuery);
		this.setDataSource(dataSource);
	}
}