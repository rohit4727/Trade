package com.iris.batch.step;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.iris.batch.model.TradeBase;
import com.iris.batch.util.PropertiesUtil;

/**
 * Class for writing spring batch processed data
 * 
 * @author Saurabh
 */
public class Writer<T extends TradeBase> extends JdbcBatchItemWriter<T> {
	private static final String INSERTION_QUERY = "insertionQuery";

	public Writer(DataSource dataSource) {
		this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		this.setSql(PropertiesUtil.get(INSERTION_QUERY));
		this.setDataSource(dataSource);
	}
}
