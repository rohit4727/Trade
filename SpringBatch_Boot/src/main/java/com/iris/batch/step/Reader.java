package com.iris.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.core.NamingPolicy;
import org.springframework.cglib.core.Predicate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.iris.batch.model.TradeBase;
import com.iris.batch.util.CSVColumns;
import com.iris.batch.util.ETLConstants;
import com.iris.batch.util.ErrorMsg;
import com.iris.batch.util.PropertiesUtil;

/**
 * Class for reading csv file for spring batch processing
 *
 * @author Saurabh Gupta
 */
public class Reader<T extends TradeBase> extends FlatFileItemReader<T> {
	private static final Logger log = LoggerFactory.getLogger(Reader.class);
	private static final String TRADE_FILE_NAME = "tradeFileName";
	private static final String LINES_TO_SKIP = "linesToSkip";

	public Reader() {

		// Set input file
		this.setResource(new FileSystemResource(PropertiesUtil.get(TRADE_FILE_NAME)));
		//this.setResource(new ClassPathResource(PropertiesUtil.get(TRADE_FILE_NAME)));

		int linesToSkip = 0;
		try {
			linesToSkip = Integer.parseInt(PropertiesUtil.get(LINES_TO_SKIP));
		} catch (NumberFormatException e) {
			log.error("NumberFormatException", e);
			throw new RuntimeException(e);
		}
		
		// Skip the file header line
		this.setLinesToSkip(linesToSkip);

		try {

			final BeanGenerator beanGenerator = new BeanGenerator();
			final String className = ETLConstants.TRADE_CLASS_NAME;

			/* use our own hard coded class name instead of a real naming policy */
			beanGenerator.setNamingPolicy(new NamingPolicy() {
				@Override
				public String getClassName(final String prefix, final String source, final Object key,
						final Predicate names) {
					return className;
				}
			});
			beanGenerator.setSuperclass(TradeBase.class);
			BeanGenerator.addProperties(beanGenerator, CSVColumns.getColumns());
			@SuppressWarnings("unchecked")
			Class<? extends T> pojoClass = (Class<? extends T>) beanGenerator.createClass();

			this.setLineMapper(new DefaultLineMapper<T>() {
				{
					setLineTokenizer(new DelimitedLineTokenizer() {
						{
							setNames(CSVColumns.getColumnNames());
						}
					});
					setFieldSetMapper(new BeanWrapperFieldSetMapper<T>() {
						{
							setTargetType(pojoClass);
						}
					});
				}
			});
		} catch (NumberFormatException e) {
			log.error(ErrorMsg.TOTAL_COL_NOT_INT);
		}
	}
}
