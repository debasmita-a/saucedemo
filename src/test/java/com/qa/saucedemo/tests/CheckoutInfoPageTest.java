package com.qa.saucedemo.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.saucedemo.constants.FrameworkConstants;

import qa.com.saucedemo.base.BaseTest;

public class CheckoutInfoPageTest extends BaseTest {

	@BeforeClass
	public void checkoutInfoPageSetup() {
		List<String> products = new ArrayList<String>(
				Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"));
		cartPage = loginPage
				.navigateToInventoryPage(prop.getProperty("username").trim(), prop.getProperty("password").trim())
				.navigateToCartPage();
		checkoutInfoPage = cartPage.navigateToCheckoutPage(products);
	}

	@Test
	public void getCheckoutInfoPageTitleTest() {
		Assert.assertEquals(checkoutInfoPage.getCheckoutInfoPageTitle(), FrameworkConstants.CHECKOUT_STEP1_PAGE_HEADER);
	}

	@Test
	public void getCheckoutPageUrlTest() {
		Assert.assertTrue(checkoutInfoPage.getCheckoutPageUrl().contains(FrameworkConstants.CHECKOUT_STEP1_PAGE_URL));
	}

	@Test
	public void clickOnCancelTest() {
		Assert.assertEquals(checkoutInfoPage.clickOnCancel(), FrameworkConstants.CART_PAGE_HEADER);
	}

	@DataProvider
	public Object[][] fillInfoTestdata() {
		return new Object[][] { { "Adhikari", "334455" } };
	}

	@Test(dataProvider = "fillInfoTestdata")
	public void fillInformationWithoutZipcodeTest(String ln, String code) {
		String msg = checkoutInfoPage.fillInformationWithoutFn(ln, code);
		Assert.assertEquals(msg, FrameworkConstants.FIRST_NAME_ERROR);
	}
}
