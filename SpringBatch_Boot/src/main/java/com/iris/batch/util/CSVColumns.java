package com.iris.batch.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Utility class to provide csv file column names It reads column names for
 * application.properties file
 *
 * @author Saurabh Gupta
 */
public class CSVColumns {
	
	private static final Logger log = LoggerFactory.getLogger(CSVColumns.class);

	private final PropertiesUtil props;

	private final Map<String, Class<?>> columns = new HashMap<>();

	public CSVColumns(PropertiesUtil props) {
		this.props = props;		

		props.getCsvProperty().forEach((String key, String val) -> {
			try {
				columns.put(key, Class.forName(val));
			} catch (ClassNotFoundException e) {
				log.error("Error", e);
			}
		});
	}
	
	public Map<String, Class<?>> getColumns() {
		return columns;
	}

	public String[] getColumnNames() {
		return props.getCsvProperty().keySet().toArray(new String[props.getTotalColumns()]);
	}

}
