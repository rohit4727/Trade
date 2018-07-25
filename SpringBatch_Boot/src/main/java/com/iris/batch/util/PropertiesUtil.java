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
	private static final String RROPERTY_FILE_PATH = "src/main/resources/application.properties";

	static {
		props = new Properties();
		try {
			FileReader reader = new FileReader(RROPERTY_FILE_PATH);
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
		return props.getProperty(key);
	}
}
