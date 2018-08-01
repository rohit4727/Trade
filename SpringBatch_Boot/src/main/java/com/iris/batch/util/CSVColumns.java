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

	private final static Map<String, Class<?>> columns = new HashMap<>();
	private static final String CSV_PROPERTY = "csvProperty";
	private static final String TOTAL_COLUMNS = "totalColumns";
	private static String[] columnNames;

	static {
		columnNames = new String[Integer.parseInt(PropertiesUtil.get(TOTAL_COLUMNS))];
		for (String csvProp : PropertiesUtil.get(CSV_PROPERTY).split(",")) {
			String[] split = csvProp.split(":");
			try {
				columns.put(split[0], Class.forName(split[1]));
				
			} catch (ClassNotFoundException e) {
				log.error("Error", e);
				throw new RuntimeException(e);
			}
		}
		columnNames = columns.keySet().toArray(columnNames);
	}

	public static Map<String, Class<?>> getColumns() {
		return columns;
	}
	
	public static String[] getColumnNames() {
		return columnNames;
	}
}
