package com.iris.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import com.iris.batch.model.MrMarketEvent;

/**
 * The Class FxMarketEventReader.
 *
 * @author Satyveer
 */
public class MrMarketEventReader extends FlatFileItemReader<MrMarketEvent> {

	public MrMarketEventReader() {
		//Set input file
		this.setResource(new ClassPathResource("SampleTrade.csv"));
		//Skip the file header line
		this.setLinesToSkip(1);
		//Line is mapped to item (FxMarketEvent) using setLineMapper(LineMapper)
		this.setLineMapper(new DefaultLineMapper<MrMarketEvent>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] {"Tradeid", "TradePrice",	"Security",	"InstrumentType", "CounterParty", "TradeSize", "TradeDate",	"TradeTime", "Currency", "Broker", "Direction",	"TradeAlias", "Contoller",	"ClearingSystem", "Clearinghouse" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<MrMarketEvent>() {
					{
						setTargetType(MrMarketEvent.class);
					}
				});
			}
		});
	}
}
