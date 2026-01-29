package com.tests;

import org.testng.Assert;

import com.base.BaseTest;

public class ModalHandlingTest extends BaseTest {

	public void handlingSingleFrame() {

		page.frameLocator("#id of singleframe").locator("xpath").fill("Handling singleframe in Playwright");

	}

	public void handlingMultipleFrames() {

		// Handling nested frames
		page.frameLocator("# id of outer frame").frameLocator("# id of inner frame").locator("xpath")
				.fill("Handling multiframes in Playwright");

	}

	public void handlingFramesUsingName() {

		// Handling nested frames
		page.frame("Value of name attribute").locator("xpath").fill("Handling frames using name attribute");

	}

	public void handlingNormalAlert() {

		// By default, Playwright will dismiss the alert automatically
		// However, to accept the alert, we need to handle this alert like below

		page.onDialog(dialog -> {

			String alertMsg = dialog.message();
			System.out.println("Alert message is:" + alertMsg);
			Assert.assertTrue(alertMsg.contains("Expected message"));
			dialog.accept();

		});

	}

	public void handlingConfirmAlert() {

		// By default, Playwright will dismiss the alert automatically
		// However, to accept the alert, we need to handle this alert like below

		// This is registering the dialog before clicking the element

		page.onDialog(dialog -> {

			String alertMsg = dialog.message();
			System.out.println("Alert message is:" + alertMsg);
			Assert.assertTrue(alertMsg.contains("Expected message"));
			// To accept
			dialog.accept();
			// To dismiss
//			dialog.dismiss();

		});

	}

	public void handlingPromptAlert() {

		// By default, Playwright will dismiss the alert automatically
		// However, to accept the alert, we need to handle this alert like below

		// This is registering the dialog before clicking the element

		page.onDialog(dialog -> {

			String alertMsg = dialog.message();
			System.out.println("Alert message is:" + alertMsg);
			Assert.assertTrue(alertMsg.contains("Expected message"));
			// To accept
			dialog.accept("Enter the value to be typed in the alert");
			// To dismiss
//			dialog.dismiss();

		});

	}

}
