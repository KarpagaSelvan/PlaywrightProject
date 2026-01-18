package com.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.pages.HomePage;
import com.pages.LoginPage;

public class LoginTest2 extends BaseTest {

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

	@Test
	public void loginTest2() {

		test.skip("Skipping this test for demo purpose");
		throw new SkipException("Skipping this test for testng report demo");
	}

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

}
