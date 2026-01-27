package com.pages;

import com.microsoft.playwright.Page;

public class ProductPage {

	private Page page;

	private final String ptoductPageTitle = "//button[text()='Back to products']";
	private final String btnAddToCart = "add-to-cart";
	

	public ProductPage(Page page) {
		this.page = page;
	}

	public String getProductPageTitle() {
		return page.locator(ptoductPageTitle).textContent();
	}

	public void clickingAddToCart() {
		page.click(btnAddToCart);
	}
	
	

}
