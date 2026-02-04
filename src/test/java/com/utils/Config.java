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

	// SINGLE SOURCE OF TRUTH
	public static String username() throws IOException {
		//For CI execution - env variables secrets
//		String ciUser = System.getenv("USERNAME");
//		if (ciUser != null && !ciUser.isEmpty()) {
//			return ciUser;
//		}

		String localUser = ConfigLoader.getConfig().getProperty("username");

		if (localUser == null || localUser.isEmpty()) {
			throw new RuntimeException("Username missing for env: " + ENV);
		}
		return localUser;
	}

	public static String password() throws IOException {
		//For CI execution - env variables secrets
//		String ciPassword = System.getenv("PASSWORD");
//		if (ciPassword != null && !ciPassword.isEmpty()) {
//			return ciPassword;
//		}

		String localPassword = ConfigLoader.getConfig().getProperty("password");

		if (localPassword == null || localPassword.isEmpty()) {
			throw new RuntimeException("Password missing for env: " + ENV);
		}
		return localPassword;

	}

	public static boolean headlessStatus() throws IOException {
		return Boolean.parseBoolean(ConfigLoader.getConfig().getProperty("headless", "true"));
	}

}
