package com.iris.batch.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to provide csv file column names It reads column names for
 * application.properties file
 *
 * @author Saurabh Gupta
 */
public class CSVColumns {
	
	private static final Logger log = LoggerFactory.getLogger(CSVColumns.class);

	private final Map<String, Class<?>> columns = new HashMap<>();
	
//	public CSVColumns(String mapValues) {
//		for(String splittedVal : mapValues.split(",")) {
//			splittedVal.trim().split(":");
//		}
//		return Splitter.on(",").omitEmptyStrings().trimResults().withKeyValueSeparator(":").split(property);
//	}

	public CSVColumns(Map<String, String> csvColMapping) {

		csvColMapping.forEach((String key, String val) -> {
			try {
				System.out.println(key+" ---------------- "+val);
				columns.put(key, Class.forName(val));
			} catch (ClassNotFoundException e) {
				log.error("Error", e);
			}
		});
	}
	
	public Map<String, Class<?>> getColumns() {
		return columns;
	}

	public String getColumnNames() {
		// TODO Auto-generated method stub
		return null;
	}

//	public String[] getColumnNames() {
//		return props.getCsvProperty().keySet().toArray(new String[props.getTotalColumns()]);
//	}

}
