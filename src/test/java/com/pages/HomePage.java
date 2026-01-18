package com.pages;

import com.microsoft.playwright.Page;

public class HomePage {

	private Page page;

//	private final String dashboardText = "oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module";
	private final String timeLink = "oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module";

	public HomePage(Page page) {
		this.page = page;
	}

//	public String getDashboardText() {
//		return page.textContent(dashboardText);
//	}

	public void clickTimeLink() {
		page.locator(timeLink).click();
	}

}
