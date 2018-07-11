package com.iris.batch.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.core.NamingPolicy;
import org.springframework.cglib.core.Predicate;
import org.springframework.core.io.ClassPathResource;

import com.iris.batch.model.TradeBase;
import com.iris.batch.util.CSVColumns;
import com.iris.batch.util.ETLConstants;
import com.iris.batch.util.ErrorMsg;

/**
 * The Class FxMarketEventReader.
 *
 * @author Satyveer
 */
public class MrMarketEventReader<T extends TradeBase> extends FlatFileItemReader<T> {
	private static final Logger log = LoggerFactory.getLogger(MrMarketEventReader.class);

	public MrMarketEventReader(String filePath) {

		// Set input file
		this.setResource(new ClassPathResource(filePath));

		// Skip the file header line
		this.setLinesToSkip(1);
		// Line is mapped to item (FxMarketEvent) using setLineMapper(LineMapper)

		try {

			final BeanGenerator beanGenerator = new BeanGenerator();
			final String className = ETLConstants.tradeClassName;

			/* use our own hard coded class name instead of a real naming policy */
			beanGenerator.setNamingPolicy(new NamingPolicy() {
				@Override
				public String getClassName(final String prefix, final String source, final Object key,
						final Predicate names) {
					return className;
				}
			});
			beanGenerator.setSuperclass(TradeBase.class);
			BeanGenerator.addProperties(beanGenerator, CSVColumns.properties);
			@SuppressWarnings("unchecked")
			Class<? extends T> pojoClass = (Class<? extends T>) beanGenerator.createClass();

			this.setLineMapper(new DefaultLineMapper<T>() {
				{
					setLineTokenizer(new DelimitedLineTokenizer() {
						{
							setNames(CSVColumns.colNames);
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
			log.error(ErrorMsg.totalColNotInt);
		}
	}
}
