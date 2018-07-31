package com.iris.batch.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for providing constants from application.properties file
 *
 * @author Saurabh Gupta
 */
public class PropertiesUtil {

	private static Properties props;
	private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
	private static final String RROPERTY_FILE_PATH = "SpringBatchBootCustom.properties";
	private static final String PROPERTY_NOT_FOUND = " not found";

	static {
		props = new Properties();
		try {
			FileReader reader = new FileReader(RROPERTY_FILE_PATH);
			System.out.println("property file reader" + reader);
			props.load(reader);
		} catch (FileNotFoundException e) {
			log.error(LogMsg.UNABLE_TO_READ_PROPERTY_FILE, e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(LogMsg.UNABLE_TO_READ_PROPERTY_FILE, e);
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		String propertyValue = props.getProperty(key);
		if (propertyValue == null || propertyValue.isEmpty()) {
			log.error(propertyValue + PROPERTY_NOT_FOUND);
			throw new RuntimeException(propertyValue + PROPERTY_NOT_FOUND);
		}
		return propertyValue;
	}
}
