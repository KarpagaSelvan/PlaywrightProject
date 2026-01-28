package com.pages;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

public class HomePage {

	private Page page;

	private final String title = "//div[text()='Swag Labs']";
	private final String backBag = "//div[text()='Sauce Labs Backpack']";
	private final String btnSorting = "//select[@class=\"product_sort_container\"]";
	private final String dropDownSorting = "product_sort_container";
	private final String btnDropDown = "//span[@class=\"active_option\"]";

	public HomePage(Page page) {
		this.page = page;
	}

	public String getDashboardText() {
		return page.locator(".app_logo").textContent();

	}

	public void clickingBackBag() {
		page.click(backBag);
	}

	// Sorting without @DataProvider

	public void sortingPriceLowToHigh(String sortingOption) {
		// Handling drop down - Type 1
		page.selectOption(btnSorting, sortingOption);
	}

//	public void sortingPriceHighToLow() {
//		// Handling drop down - Type 1
//		page.selectOption(btnSorting, "hilo");
//	}
//
//	public void sortingNameZtoA() {
//		// Handling drop down - Type 2
//		page.locator(dropDownSorting).selectOption("za");
//
//	}
//
//	public void sortingNameAtoZ() {
//		// Handling drop down - Type 2
//		page.locator(dropDownSorting).selectOption("az");
//
//	}

	public String gettingDropDownText() {
		return page.locator(btnDropDown).textContent();

	}

}
