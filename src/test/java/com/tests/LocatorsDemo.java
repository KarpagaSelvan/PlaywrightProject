package com.tests;

import org.testng.annotations.Test;

import com.base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LocatorsDemo extends BaseTest {

	@Test
	public void testAllLocators() {

		page.navigate("https://trytestingthis.netlify.app/");

		// Id locator
		page.locator("#fname").fill("John");

		// Name locator
		page.locator("input[name=\"lname\"]").fill("Wick");

		// Value locator - to select the radio button
		page.locator("input[value=\"male\"]").check();

		// Using index to locate the 2nd radio button - nth index starts from 0
		page.locator("input[type=\"radio\"]").nth(1).check();

		// Handling drop-down
		page.locator("select#option").selectOption("Option 2");
		page.locator("select[name=\"option\"]").selectOption("Option 3");

		// Handling check box
		page.locator("input[type=\"checkbox\"][value=\"Option 3\"]").check();
		page.locator("input[name=\"option1\"]").check();
		// Selecting check box using label text
		page.getByLabel("Option 2").check();

		// Xpath locator
		page.locator("//input[@id=\"day\"]").fill("2025-12-31");

		// Text to locate
		Locator fetchedText = page.getByText("Some text..");
		System.out.println("Fetched Text: " + fetchedText);

		// getByRole locator
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();

		// To pause the execution
		page.pause();
	}

}
