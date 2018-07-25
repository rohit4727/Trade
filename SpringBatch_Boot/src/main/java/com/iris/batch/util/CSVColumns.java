package com.iris.batch.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iris.batch.step.Reader;

/**
 * Utility class to provide csv file column names It reads column names for
 * application.properties file
 *
 * @author Saurabh Gupta
 */
public class CSVColumns {
	private static final Logger log = LoggerFactory.getLogger(Reader.class);

	public final static Map<String, Class<?>> properties = new HashMap<String, Class<?>>();
	public final static String[] colNames;

	static {
		String cols = PropertiesUtil.get(ETLConstants.TOTAL_COLUMN);
		if (cols == null || cols.isEmpty()) {
			log.error(ErrorMsg.TOTAL_CAL_NOT_FOUND);
			throw new RuntimeException(ErrorMsg.TOTAL_CAL_NOT_FOUND);
		}

		try {
			int columns = Integer.parseInt(cols);

			String[] columnNames = new String[columns];
			colNames = new String[columns];

			for (int i = 0; i < columns; i++) {
				columnNames[i] = PropertiesUtil.get(ETLConstants.COLUMN + (i + 1));
				if (columnNames[i] == null || columnNames[i].isEmpty()) {
					log.error(ETLConstants.COLUMN + i + ErrorMsg.NOT_FOUND_IN_PROP_FILE);
					throw new RuntimeException();
				}

				String[] colNameAndTypes = columnNames[i].split(",");

				properties.put(colNameAndTypes[0],
						colNameAndTypes[1] != null ? Class.forName(colNameAndTypes[1]) : String.class);
				colNames[i] = colNameAndTypes[0];
			}
		} catch (NumberFormatException e) {
			log.error(ErrorMsg.TOTAL_COL_NOT_INT);
			throw new RuntimeException(ErrorMsg.TOTAL_COL_NOT_INT);
		} catch (ClassNotFoundException e) {
			log.error("Error", e);
			throw new RuntimeException(e);
		}
	}
}
