package com.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {

	private static final String ENV = System.getProperty("env", "qa");

	public static String env() {
		return ENV;
	}

	public static Path authStatePath() {
		Path path = Paths.get("auth/auth-" + ENV + ".json");

		if (!Files.exists(path)) {
			throw new RuntimeException("Auth state missing for env: " + ENV + ". Run AuthSetup first.");

		}
		return path;
	}

	public static String baseURL() throws IOException {
		return ConfigLoader.getConfig().getProperty("base.url");
	}

	public static String userName() throws IOException {
		return ConfigLoader.getConfig().getProperty("usernname");
	}

	public static String password() throws IOException {
		return ConfigLoader.getConfig().getProperty("password");
	}

	public static boolean headlessStatus() throws IOException {
		return Boolean.parseBoolean(ConfigLoader.getConfig().getProperty("headless", "true"));
	}

}
