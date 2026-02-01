package com.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class CrossBrowserTest {

	Playwright playwright;
	Browser browser;
	Page page;
	BrowserType launchingBrowser;

	@BeforeMethod
	@Parameters({ "browserType" })
	public void setup(String browserType) {

		playwright = Playwright.create();
		switch (browserType.toLowerCase()) {
		case "chrome":
			launchingBrowser = playwright.chromium();
			break;
		case "firefox":
			launchingBrowser = playwright.firefox();
			break;
		case "safari":
			launchingBrowser = playwright.webkit();
			break;
		default:
			System.out.println("Invalid browser type:" + browserType);
		}

		browser = launchingBrowser.launch(new BrowserType.LaunchOptions().setHeadless(false));
		page = browser.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

	}

	@Test
	public void getTitle() {

		String pageTitle = page.title();
		Assert.assertEquals("OrangeHRM", pageTitle, "Verifying the page title");

	}

	@Test
	public void loginTest() {

		page.locator("//input[@name=\"username\"]").fill("Admin");
		page.locator("//input[@name=\"password\"]").fill("admin123");
		page.locator("//button[@type=\"submit\"]").click();

	}

	@AfterMethod
	public void tearDown() {
		page.close();
		browser.close();
		playwright.close();
	}

}
