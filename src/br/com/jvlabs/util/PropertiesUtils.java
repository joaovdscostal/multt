package br.com.jvlabs.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

	public static Properties loadProperties(String properties_file) {
		Properties properties = new Properties();

		InputStream inputStream = PropertiesUtils.class.getResourceAsStream(properties_file);

		if (inputStream == null) {
			inputStream = PropertiesUtils.class.getResourceAsStream("/" + properties_file);
		}

		try {
			properties.load(inputStream);
		} catch (Exception e) {}

		return properties;
	}

}
