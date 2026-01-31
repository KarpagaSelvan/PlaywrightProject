package com.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Download;
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

//	@Test
//	public void downloadingFileTest(){

	public void downloadingFileTest() throws IOException {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		page.navigate("https://the-internet.herokuapp.com/download");
		System.out.println("The title of the page is:" + page.title());

		Download download = page.waitForDownload(() -> {
			page.locator("//a[@href=\"download/some-file.txt\"]").click();
		});

		String downloadPath = System.getProperty("user.dir") + "//downloadedFiles/" + download.suggestedFilename();

		System.out.println("The downloaded path would be:" + downloadPath);

//		System.out.println(download.suggestedFilename());
//		System.out.println(download.url());
//		System.out.println(download.path());

		// Saving the file in the desired location
		download.saveAs(Paths.get(downloadPath));

		// Assertions can be done for the file like below
		// Size, Content, Name, Extension...etc

		// Assertion for extension
		if (downloadPath.endsWith(".txt")) {
			System.out.println("File extension verified");
		} else {
			System.out.println("File extension verification failed");
			browser.close();
			return;
		}

		// Assertion for size
		if (Files.size(Path.of(downloadPath)) > 0) {
			System.out.println("File size verified");
		} else {
			System.out.println("File size verification failed");
			browser.close();
			return;
		}

		// Assertion for content
		String downloadedFileContent = Files.readString(Path.of(downloadPath));
//		System.out.println(downloadedFileContent);

		if (downloadedFileContent.contains("\\rtf1\\ansi\\ansicpg1252\\cocoartf1348")) {
			System.out.println("File content verified");
		} else {
			System.out.println("File content verification failed");
			browser.close();
			return;
		}

		browser.close();
	}

}
