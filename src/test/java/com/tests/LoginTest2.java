package com.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.pages.HomePage;
import com.pages.LoginPage;

//@Test(retryAnalyzer = com.retry.Retry.class) --> For applying retry to all tests in the class
public class LoginTest2 extends BaseTest {

//	@Test(retryAnalyzer = com.retry.Retry.class) --> For applying retry to individual test
	@Test
	public void loginTest1() {

		test.info("Navigating to the Website");
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		test.info("Emtering Username and Password and Clicking on Login Button");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("Admin");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("admin123");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
	}

//	@Test(retryAnalyzer = com.retry.Retry.class) --> For applying retry to individual test
	@Test
	public void loginTest2() {

		test.skip("Skipping this test for demo purpose");
		throw new SkipException("Skipping this test for testng report demo");
	}

//	@Test(retryAnalyzer = com.retry.Retry.class) --> For applying retry to individual test
	@Test
	public void loginTest3() {

		// This test will fail due to incorrect creds

		test.info("Navigating to the Website");
//		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		test.info("Emtering Username and Password and Clicking on Login Button");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("Admin");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("admin123467890876");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

	}

//	@Test(retryAnalyzer = com.retry.Retry.class) --> For applying retry to individual test
	@Test
	public void loginTest4() {

		// ID locator
		page.locator("id=\"oxd-toaster_1\"").click();

		// class locator
		page.locator(".oxd-button oxd-button--medium").click();

		// text locator
		page.locator("text=Login").click();

		// css locator
		// tag and attribute
		page.locator("input[placeholder='Username']").fill("Admin");

		// tag and class
		page.locator("button.oxd-button").click();

		// tag and class and attribute
		page.locator("button.oxd-button[type='submit']").click();

		// xpath locator
		page.locator("//input[@name='username']").fill("Admin");

		// test data id locator
		// Using data-test-id attribute
		page.locator("data-test-id=username").fill("Admin");
		// Using an inbuilt method in Playwright
		page.getByTestId("value of test id attribute").click();

		// getByRole locator
		// Using codegen tool
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

	}

}
