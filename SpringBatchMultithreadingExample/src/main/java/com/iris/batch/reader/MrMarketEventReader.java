package com.iris.batch.reader;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.util.Assert;

import com.iris.batch.model.TradeBase;
import com.iris.batch.util.PropertiesUtil;

/**
 * The Class FxMarketEventReader.
 *
 * @author Satyveer
 */
public class MrMarketEventReader<T extends TradeBase> extends FlatFileItemReader<T> {
	private static final Logger log = LoggerFactory.getLogger(MrMarketEventReader.class);

	public MrMarketEventReader() {

		// Set input file
		this.setResource(new ClassPathResource("SampleTrade.csv"));
		// Skip the file header line
		this.setLinesToSkip(1);
		// Line is mapped to item (FxMarketEvent) using setLineMapper(LineMapper)

		String cols = PropertiesUtil.get("csvmapping.totalColumns");
		Assert.isNull(cols, "csvmapping.totalColumns is not found in properties file");
		try {
			int columns = Integer.parseInt(cols);

			String[] columnNames = new String[columns];
			for (int i = 0; i < columns; i++) {
				columnNames[i] = PropertiesUtil.get("csvmapping.column" + (i + 1));
				Assert.isNull(columnNames[i],
						"csvmapping.column" + (i + 1) + " is not defined in application.properties");
			}

			final BeanGenerator beanGenerator = new BeanGenerator();
			final String className = "Trade";
			

			final Map<String, Class<?>> properties = new HashMap<String, Class<?>>();
			properties.put("foo", Integer.class);
			properties.put("bar", String.class);
			properties.put("baz", int[].class);

			/* use our own hard coded class name instead of a real naming policy */
			beanGenerator.setNamingPolicy(new NamingPolicy() {
				@Override
				public String getClassName(final String prefix, final String source, final Object key,
						final Predicate names) {
					return className;
				}
			});
			beanGenerator.setSuperclass(TradeBase.class);
			BeanGenerator.addProperties(beanGenerator, properties);
			Class<?> pojoClass = (Class<?>) beanGenerator.createClass();

			this.setLineMapper(new DefaultLineMapper<T>() {
				{
					setLineTokenizer(new DelimitedLineTokenizer() {
						{
							setNames(columnNames);
						}
					});
//					setFieldSetMapper(new BeanWrapperFieldSetMapper<T>() {
//						{
//							setTargetType(T);
//						}
//					});
				}
			});
		} catch (NumberFormatException e) {
			log.error("csvmapping.totalColumns is not integer value in application.properties");
		}
	}
}
