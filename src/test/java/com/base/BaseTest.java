package com.base;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
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

	@BeforeMethod
	public void setUp(Method method) throws Exception {

		// Reporting
		extent = ExtentManager.getInstance();
		test = extent.createTest(method.getName());

		// Playwright Setup
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		page = browser.newPage();

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
		extent.flush();

		// Browser & Playwright cleaning
		if (browser != null)
			browser.close();
		if (playwright != null)
			playwright.close();

	}

}
