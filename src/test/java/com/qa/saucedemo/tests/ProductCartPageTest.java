package com.qa.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.com.saucedemo.base.BaseTest;

public class ProductCartPageTest extends BaseTest{

	@BeforeClass
	public void productCartPageSetup() {
		cartPage = loginPage.navigateToInventoryPage(prop.getProperty("username").trim(), prop.getProperty("password").trim())
		         .navigateToCartPage();	
	}
	
	@Test
	public void isContinueShoppingBtnAvailableTest() {
		Assert.assertTrue(cartPage.isContinueShoppingBtnAvailable());
	}
	
	@Test
	public void isCheckoutBtnAvailableTest() {
		Assert.assertTrue(cartPage.isCheckoutBtnAvailable());
	}
	
	@DataProvider
	public Object[][] cartTestdata(){
		return new Object[][] {
			{"Sauce Labs Backpack"},
			{"Sauce Labs Bike Light"},
			{"Sauce Labs Bolt T-Shirt"},
			{"Sauce Labs Fleece Jacket"}
		};
	}
	
	@Test(dataProvider = "cartTestdata")
	public void getCartItemCountTest(String productname) {
		inventoryPage.addAProductToCart(productname);
		Assert.assertEquals(cartPage.getCartItemCount(), inventoryPage.getShoppingCartItemCount());
	}
}
