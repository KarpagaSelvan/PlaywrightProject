package com.tests;

import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pages.LoginPage;

public class LoginTest extends BaseTest {

	private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

	@Test(description = "Login with valid admin credentials")
	public void loginTest() {

		LoginPage loginPage = new LoginPage(page);

		test.info("Navigating to the website");
		page.navigate(BASE_URL);

		test.info("Logging in to the application");
		loginPage.login("Admin", "admin123");

//		System.out.println(homePage.getDashboardText());
//		homePage.clickTimeLink();
	}
}