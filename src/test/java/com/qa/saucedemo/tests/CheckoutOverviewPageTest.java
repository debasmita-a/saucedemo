package com.qa.saucedemo.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import qa.com.saucedemo.base.BaseTest;

public class CheckoutOverviewPageTest extends BaseTest{

	@BeforeClass
	public void checkoutOverviewPageSetup() {
		List<String> products = new ArrayList<String>(
				Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"));
		cartPage = loginPage
				.navigateToInventoryPage(prop.getProperty("username").trim(), prop.getProperty("password").trim())
				.navigateToCartPage();
		checkoutInfoPage = cartPage.navigateToCheckoutPage(products);
		checkoutOverviewPage = checkoutInfoPage.clickOnContinue("Debasmita", "Adhikari", "226025");
	}
	
	@Test
	public void extractPriceValueTest() {
		checkoutOverviewPage.extractPriceValue();
	}
}
