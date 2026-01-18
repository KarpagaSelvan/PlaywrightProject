package com.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	private Page page;
	private final String userNameTextBox = "input[name='username']";
	private final String passwordTextBox = "input[name='password']";
	private final String buttonLogin = "button[type='submit']";

	// Cnstructor
	public LoginPage(Page page) {
		this.page = page;
	}

	public void enterUsername(String userName) {
		page.fill(userNameTextBox, userName);
	}

	public void enterPassword(String password) {
		page.fill(passwordTextBox, password);
	}

	public void clickLogin() {
		page.click(buttonLogin);
	}

	// Its our choice to create this method

	public void login(String userName, String password) {

		enterUsername(userName);
		enterPassword(password);
		clickLogin();

	}

}
