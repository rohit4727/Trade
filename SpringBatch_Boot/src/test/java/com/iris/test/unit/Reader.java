package com.iris.test.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Callable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.iris.batch.model.TradeBase;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Reader {

	private static final Logger log = LoggerFactory.getLogger(Reader.class);

	@Autowired
	private ItemReader<? extends TradeBase> reader;

	@Test
	public void testReader() {

		ExecutionContext executionContext = new ExecutionContext();
		((ItemStream) reader).open(executionContext);

		// The reader is initialized and bound to the input data
		try {
			assertNotNull(reader.read());

			StepExecution stepExecution = MetaDataInstanceFactory.createStepExecution();

			Callable<Integer> callable = () -> {

				int count = 0;

				while (reader.read() != null) {
					count++;
				}
				return count;
			};
			
			int count = StepScopeTestUtils.doInStepScope(stepExecution, callable);

			assertTrue(count > 0);
		} catch (Exception e) {
			log.error("Error", e);
		}
	}
}
