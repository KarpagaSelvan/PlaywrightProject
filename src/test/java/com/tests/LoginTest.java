package com.tests;

import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pages.HomePage;
import com.pages.LoginPage;

public class LoginTest extends BaseTest {

	@Test
	public void loginTest() {

		LoginPage loginPage = new LoginPage(page);
//		HomePage homePage = new HomePage(page);

		test.info("Navigating to the Website");
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		test.info("Logging in to the application");
		loginPage.login("Admin", "admin123");
//		System.out.println(homePage.getDashboardText());
//		homePage.clickTimeLink();
	}
}
