package com.tests;

import org.testng.annotations.Test;

import com.base.BaseTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightJavaTest extends BaseTest {

	@Test
	public void viewTitle() {

		page.navigate("https:www.google.com");
		System.out.println("The page title is:"+page.title());

	}

//	public static void main(String[] args) {
//
//		try (Playwright playwright = Playwright.create()) {
//
//			Browser browser = playwright.chromium()
//					.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
//			Page page = browser.newPage();
//			page.navigate("https://www.google.com");
//			System.out.println("The page title is:"+page.title());
//
//			System.out.println("Playwright Java setup is successful!");
//		}
//
//	}

}
