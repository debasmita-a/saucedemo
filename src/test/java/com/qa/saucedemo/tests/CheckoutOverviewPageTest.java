package com.qa.saucedemo.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.saucedemo.constants.FrameworkConstants;

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
	public void getPriceMetadataTest() {
		checkoutOverviewPage.getPriceMetadata();
	}
	
	@Test
	public void getItemOrderMetadataTest() {
		checkoutOverviewPage.getItemOrderMetadata();
	}
	
	@Test
	public void getPageTitleTest() {
		Assert.assertEquals(checkoutOverviewPage.getPageTitle(), FrameworkConstants.CHECKOUT_STEP2_PAGE_HEADER);
	}
	
	@Test
	public void getPageUrlTest() {
		Assert.assertTrue(checkoutOverviewPage.getPageUrl().contains(FrameworkConstants.CHECKOUT_STEP2_PAGE_URL));
	}
	
	@Test
	public void clickOnCancelBtnTest() {
		Assert.assertEquals(checkoutOverviewPage.clickOnCancelBtn(), FrameworkConstants.CHECKOUT_STEP1_PAGE_HEADER);
	}
	
	@Test
	public void getPricesOfAllItemsTest() {
		checkoutOverviewPage.getPricesOfAllItems();
	}
	
	@Test
	public void verifyTotalItemPriceTest() {
		Assert.assertTrue(checkoutOverviewPage.verifyTotalItemPrice());
	}
}
