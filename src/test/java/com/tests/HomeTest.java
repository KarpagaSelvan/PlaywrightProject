package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.dataprovider.DataSupplier;
import com.pages.HomePage;
import com.pages.ProductPage;

public class HomeTest extends BaseTest {

	private final String BASE_URL = "https://www.saucedemo.com/inventory.html";

	@Test(priority = 1)
	public void gettingTitleTest() {

		page.navigate(BASE_URL);
		page.waitForLoadState();
		HomePage homePage = new HomePage(page);
		System.out.println(homePage.getDashboardText());

		Assert.assertEquals(homePage.getDashboardText(), "Swag Labs", "Verifying the title of the Home page");

	}

	@Test(priority = 2)
	public void clickingBackBagTest() {

		page.navigate(BASE_URL);
		page.waitForLoadState();
		HomePage homePage = new HomePage(page);
		homePage.clickingBackBag();
		ProductPage productPage = new ProductPage(page);
		System.out.println(productPage.getProductPageTitle());

		Assert.assertEquals(productPage.getProductPageTitle(), "Back to products",
				"Verifying the title of the Product page");

	}

	@Test(priority = 3, dataProvider = "sortingOptionsDataFromExcel", dataProviderClass = DataSupplier.class)
	public void sortingOptions(String sortingOption, String expectedActiveOption) {

		page.navigate(BASE_URL);
		page.waitForLoadState();
		HomePage homePage = new HomePage(page);
		homePage.sortingPriceLowToHigh(sortingOption);
		System.out.println(homePage.gettingDropDownText());

		Assert.assertEquals(homePage.gettingDropDownText(), expectedActiveOption,
				"Verifying the sorting low to high option selected");

	}

//	@Test(priority = 4)
//	public void sortingHighToLow() {
//
//		page.navigate(BASE_URL);
//		page.waitForLoadState();
//		HomePage homePage = new HomePage(page);
//		homePage.sortingPriceHighToLow();
//		System.out.println(homePage.gettingDropDownText());
//
//		Assert.assertEquals(homePage.gettingDropDownText(), "Price (high to low)",
//				"Verifying the sorting high to low option selected");
//
//	}
//
//	@Test(priority = 5)
//	public void sortingNameZtoATest() {
//
//		page.navigate(BASE_URL);
//		page.waitForLoadState();
//		HomePage homePage = new HomePage(page);
//		homePage.sortingNameZtoA();
//		System.out.println(homePage.gettingDropDownText());
//
//		Assert.assertEquals(homePage.gettingDropDownText(), "Name (Z to A)",
//				"Verifying the sorting Z to A option selected");
//
//	}
//
//	@Test(priority = 6)
//	public void sortingNameAtoZTest() {
//
//		page.navigate(BASE_URL);
//		page.waitForLoadState();
//		HomePage homePage = new HomePage(page);
//		homePage.sortingNameAtoZ();
//		System.out.println(homePage.gettingDropDownText());
//
//		Assert.assertEquals(homePage.gettingDropDownText(), "Name (A to Z)",
//				"Verifying the sorting A to Z option selected");
//
//	}

}
