package com.iris.batch.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iris.batch.reader.MrMarketEventReader;

public class CSVColumns {
	private static final Logger log = LoggerFactory.getLogger(MrMarketEventReader.class);

	public final static Map<String, Class<?>> properties = new HashMap<String, Class<?>>();

	{
		String cols = PropertiesUtil.get(ETLConstants.totalColumn);
		if (cols == null || cols.isEmpty()) {
			log.error(ErrorMsg.totalColNotFound);
			throw new RuntimeException(ErrorMsg.totalColNotFound);
		}

		try {
			int columns = Integer.parseInt(cols);

			String[] columnNames = new String[columns];

			final Map<String, Class<?>> properties = new HashMap<String, Class<?>>();
			for (int i = 0; i < columns; i++) {
				columnNames[i] = PropertiesUtil.get(ETLConstants.column + (i + 1));
				if (columnNames[i] == null || columnNames[i].isEmpty()) {
					log.error(ETLConstants.column + i + ErrorMsg.notFoundInPropFile);
					throw new RuntimeException();
				}

				String[] colNameAndTypes = columnNames[i].split(",");

				properties.put(colNameAndTypes[0],
						colNameAndTypes[1] != null ? Class.forName(colNameAndTypes[1]) : String.class);
			}
		} catch (NumberFormatException e) {
			log.error(ErrorMsg.totalColNotInt);
			throw new RuntimeException(ErrorMsg.totalColNotInt);
		} catch (ClassNotFoundException e) {
			log.error("Error", e);
			throw new RuntimeException(e);
		}
	}
}
