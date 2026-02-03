package com.setup;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.utils.Config;

public class AuthSetup {

	Browser browser;

	@Test
	@Parameters({ "browser" })
	public void generateAuthState(String browserType) throws IOException {

		// Launch browser and perform login to generate auth state
		Playwright playwright = Playwright.create();
//		boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));

		switch (browserType.toLowerCase()) {
		case "chrome":
			browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setHeadless(Config.headlessStatus()));
			break;
		case "firefox":
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(Config.headlessStatus()));
			break;

		default:
			throw new IllegalArgumentException("Incorrect browser: " + browserType);

		}

//		((LaunchOptions) browser).setSlowMo(1000);

		// Create a new browser context for the login session
		BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions());
		Page page = browserContext.newPage();

		// Navigate to the login page
		page.navigate(Config.baseURL());

		page.fill("input[id='user-name']", Config.username());
		page.fill("input[name='password']", Config.password());
		page.click("input[type='submit']");

		// Wait for navigation to complete after login
		page.waitForURL("**/inventory.html");

		// Save the authentication state to a file auth.json in the project root
		// directory
		String env = System.getProperty("env", "qa");
		Path authFilePath = Paths.get("auth/auth-" + env + ".json");
		browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(authFilePath));

		// Close browser
//		browser.close();

	}

}
