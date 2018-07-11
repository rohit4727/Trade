package com.iris.batch.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {

	private static Properties props;
	private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

	{
		FileReader reader = null;
		props = new Properties();
		try {
			reader = new FileReader("db.properties");
			props.load(reader);
		} catch (FileNotFoundException e) {
			log.error("Unable to read property file", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Unable to load property file", e);
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}
}
