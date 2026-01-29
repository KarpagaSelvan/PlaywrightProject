package com.setup;

import java.nio.file.Paths;
import java.util.Arrays;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class AuthSetup {

	Browser browser;

	@Test
	@Parameters({ "browser" })
	public void generateAuthState(String browserType) {

		// Launch browser and perform login to generate auth state
		Playwright playwright = Playwright.create();
		boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));

		switch (browserType.toLowerCase()) {
		case "chrome":
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
			break;
		case "firefox":
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
			break;

		default:
			throw new IllegalArgumentException("Incorrect browser: " + browserType);

		}

//		((LaunchOptions) browser).setSlowMo(1000);

		// Create a new browser context for the login session
		BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions());
		Page page = browserContext.newPage();

		// Navigate to the login page
		page.navigate("https://www.saucedemo.com/");

		page.fill("input[id='user-name']", "standard_user");
		page.fill("input[name='password']", "secret_sauce");
		page.click("input[type='submit']");

		// Wait for navigation to complete after login
		page.waitForURL("**/inventory.html");

		// Save the authentication state to a file auth.json in the project root
		// directory
		browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));

		// Close browser
//		browser.close();

	}

}
