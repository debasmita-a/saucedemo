package com.qa.saucedemo.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.saucedemo.constants.FrameworkConstants;

import qa.com.saucedemo.base.BaseTest;

public class CheckoutCompletePageTest extends BaseTest {

	@BeforeClass
	public void checkoutCompletePageSetup() {
		List<String> products = new ArrayList<String>(
				Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"));
		cartPage = loginPage
				.navigateToInventoryPage(prop.getProperty("username").trim(), prop.getProperty("password").trim())
				.navigateToCartPage();
		checkoutInfoPage = cartPage.navigateToCheckoutPage(products);
		checkoutOverviewPage = checkoutInfoPage.clickOnContinue("Debasmita", "Adhikari", "226025");
		checkoutCompletePage = checkoutOverviewPage.clickOnFinishBtn();
	}
	
	@Test
	public void getPageUrlTest() {
		Assert.assertTrue(checkoutCompletePage.getPageUrl().contains(FrameworkConstants.CHECKOUT_COMPLETE_PAGE_URL));
	}
	
	@Test
	public void getPageTitleTest() {
		Assert.assertEquals(checkoutCompletePage.getPageTitle(), FrameworkConstants.CHECKOUT_COMPLETE_PAGE_HEADER);
	}
	
	@Test
	public void getCompletionHeaderMsgTest() {
		Assert.assertEquals(checkoutCompletePage.getCompletionHeaderMsg(), FrameworkConstants.ORDER_COMPLETION_HEADER);
	}
	
	@Test
	public void getCompletionTextTest() {
		Assert.assertEquals(checkoutCompletePage.getCompletionText(), FrameworkConstants.ORDER_COMPLETION_TEXT);
	}
	
	@Test
	public void isImageAvailableTest() {
		Assert.assertTrue(checkoutCompletePage.isImageAvailable());
	}
	
	@Test
	public void clickOnBackHomeBtnTest() {
		Assert.assertEquals(checkoutCompletePage.clickOnBackHomeBtn(), FrameworkConstants.INVENTORY_PAGE_HEADER);
	}
	
	@Test(enabled = false)
	public void clickOnLogoutBtnTest() {
		Assert.assertTrue(checkoutCompletePage.clickOnLogoutBtn());
	}
}
