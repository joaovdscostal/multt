package br.com.jvlabs.util;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

	private FileUtils() {

	}

	public static InputStream loadFile(String propertiesFile) throws IOException {

		InputStream inputStream = FileUtils.class.getResourceAsStream(propertiesFile);

		if (inputStream == null) {
			inputStream = FileUtils.class.getResourceAsStream("/" + propertiesFile);
		}

		return inputStream;
	}

}
