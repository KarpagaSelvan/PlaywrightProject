package com.setup;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class AuthSetup {

	@Test
	public void generateAuthState() {

		// Launch browser and perform login to generate auth state
		Playwright playwright = Playwright.create();
		boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));

		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
//		((LaunchOptions) browser).setSlowMo(1000);

		// Create a new browser context for the login session
		BrowserContext browserContext = browser.newContext();
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
