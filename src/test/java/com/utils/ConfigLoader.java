package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

	private static Properties properties;

	private ConfigLoader() {
	}

	public static Properties getConfig() throws IOException {

		if (properties == null) {
			loadProperties();
		}
		return properties;

	}

	private static void loadProperties() {
		try {
			String env = System.getProperty("env", "qa");
			String file = "config/config-" + env + ".properties";

			System.out.println("Loading config from classpath: " + file);

			InputStream resource = ConfigLoader.class.getClassLoader().getResourceAsStream(file);

			if (resource == null) {
				throw new RuntimeException("Config file not found in classpath: " + file);
			}

			properties = new Properties();
			properties.load(resource);

			System.out.println("Final ENV used (from Config Loader class): " + System.getProperty("env"));

		} catch (Exception e) {
			throw new RuntimeException("Failed to load config file", e);
		}
	}
}
