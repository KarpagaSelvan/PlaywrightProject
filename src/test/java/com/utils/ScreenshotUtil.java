package com.utils;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.microsoft.playwright.Page;

public class ScreenshotUtil {

	public static String takeScreenshot(Page page, String testName) {

		// Time stamp to update the date and time in the report everytime during
		// execution
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHMMSS").format(new Date());

		// Setting up the path to store the screenshot
		String path = "test-output/screenshots/" + testName + "_" + timestamp + ".jpg";

//		String projectPath = System.getProperty("user.dir");
//		String absolutePath = projectPath + "/" + path;

		// Taking screenshot
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

		return path;
	}

}
