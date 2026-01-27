package com.base;

import java.lang.reflect.Method;
import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.utils.ExtentManager;
import com.utils.ScreenshotUtil;

public class BaseTest {

	protected Playwright playwright;
	protected Browser browser;
	protected Page page;
	protected ExtentReports extent;
	protected ExtentTest test;
	protected BrowserContext context;

	@BeforeSuite
	public void setupSuite() {
		extent = ExtentManager.getInstance();
	}

	@BeforeMethod
	public void setUp(Method method) throws Exception {

		// Reporting before starting the method execution
		test = extent.createTest(method.getName());

		boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

		// Playwright Setup
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless).setSlowMo(1000));
//		page = browser.newPage();
//
//		// Loging the setup steps
//
//		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//		test.info("Navigated to the URL");

		context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("auth.json")));

		page = context.newPage();

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {

		// Reporting and different test status

		if (result.getStatus() == ITestResult.FAILURE) {
			test.fail(result.getThrowable()); // This method will add the exception/ error in the report

			String screenshotPath = ScreenshotUtil.takeScreenshot(page, result.getTestName());

			String projectPath = System.getProperty("user.dir");
			String absoluteScreenshotPath = projectPath + "/" + screenshotPath;

			test.addScreenCaptureFromPath(absoluteScreenshotPath, "Screenshot of failure");

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed");

			String screenshotPath = ScreenshotUtil.takeScreenshot(page, result.getTestName());

			String projectPath = System.getProperty("user.dir");
			String absoluteScreenshotPath = projectPath + "/" + screenshotPath;

			test.addScreenCaptureFromPath(absoluteScreenshotPath, "Screenshot of passed test");

		} else {
			test.skip("Test Skipped");
		}
//		extent.flush();

		// Browser & Playwright cleaning
		if (browser != null)
			browser.close();
		if (playwright != null)
			playwright.close();

	}

	@AfterSuite
	public void teardownSuite() {
		extent.flush();
	}

}
