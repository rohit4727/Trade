package com.iris.batch.writer;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.iris.batch.model.TradeBase;
import com.iris.batch.util.ErrorMsg;
import com.iris.batch.util.PropertiesUtil;

/**
 * The Class StockVolumeAggregator.
 * 
 * @author Satyveer
 */
public class TradeWriter<T extends TradeBase> extends JdbcBatchItemWriter<T> {
	private static final Logger log = LoggerFactory.getLogger(TradeWriter.class);

	public TradeWriter(DataSource dataSource) {
		String insertionQuery = PropertiesUtil.get("insertion.query");
		if (insertionQuery == null || insertionQuery.isEmpty()) {
			log.error(ErrorMsg.insertionQueryNotFound);
			throw new RuntimeException();
		}
		this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		this.setSql(insertionQuery);
		this.setDataSource(dataSource);
	}
}
