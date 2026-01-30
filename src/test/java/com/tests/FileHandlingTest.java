package com.tests;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.base.BaseTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FileHandlingTest {

	public void uploadingFileTest() {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		page.navigate("https://the-internet.herokuapp.com/upload");

		System.out.println("Page title is:" + page.title());

		// This setInputFiles method expecting locator --> Older approach
		page.setInputFiles("#file-upload",
				Paths.get(System.getProperty("user.dir") + File.separator + "filename.extension"));

		// Newer approach using locator based setInputFiles
		// Uploading a single file --> When input tag is available in HTML
		page.locator("#file-upload")
				.setInputFiles(Path.of(System.getProperty("user.dir") + "//UploadingFiles//Laptop.jpg"));

		// Uploading multiple files --> When input tag is available in HTML
		Path[] files = { Path.of(System.getProperty("user.dir") + "//UploadingFiles//Laptop.jpg"),
				Path.of(System.getProperty("user.dir") + "//UploadingFiles//Laptop1.jpg"),
				Path.of(System.getProperty("user.dir") + "//UploadingFiles//Laptop2.jpg") };

		page.locator("#file-upload").setInputFiles(files);

		// To remove the selected files --> When input tag is available in HTML
		page.locator("#file-upload").setInputFiles(new Path[0]);

		///////////////////////////////////////////////////////////////////////////////////

		// Newer approach using locator based setInputFiles
		// Uploading a single file --> When input tag is not available in HTML
		FileChooser fileChooser = page.waitForFileChooser(() -> page.locator("#drag-drop-upload").click());
//		fileChooser.setFiles(Paths.get(System.getProperty("user.dir") + "//UploadingFiles//Laptop.jpg"));

		// Uploading a multiple files --> When input tag is not available in HTML
		fileChooser.setFiles(files);

	}

}
