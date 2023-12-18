package com.qa.saucedemo.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.saucedemo.constants.FrameworkConstants;

import qa.com.saucedemo.base.BaseTest;

public class ProductCartPageTest extends BaseTest{

	@BeforeClass
	public void productCartPageSetup() {
		inventoryPage = loginPage.navigateToInventoryPage(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		cartPage = inventoryPage.navigateToCartPage();
		         
	}
	
	@Test
	public void isContinueShoppingBtnAvailableTest() {
		Assert.assertTrue(cartPage.isContinueShoppingBtnAvailable());
	}
	
	@Test
	public void isCheckoutBtnAvailableTest() {
		Assert.assertTrue(cartPage.isCheckoutBtnAvailable());
	}
	
	@Test
	public void getCartPageTitleTest() {
		Assert.assertEquals(cartPage.getCartPageTitle(), FrameworkConstants.CART_PAGE_HEADER);
	}
	
	@Test
	public void getCartPageUrlTest() {
		Assert.assertTrue(cartPage.getCartPageUrl().contains(FrameworkConstants.CART_PAGE_URL));
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
		Assert.assertEquals(cartPage.getCartItemCount(productname), inventoryPage.getShoppingCartItemCount());
	}
	
	@Test
	public void clickOnContinueShoppingBtnTest() {
		Assert.assertTrue(cartPage.clickOnContinueShoppingBtn().equals(FrameworkConstants.INVENTORY_PAGE_HEADER));
	}
	
	@DataProvider
	public Object[][] productTestdata(){
		return new Object[][] {
			{"Sauce Labs Backpack", "$29.99", "carry.allTheThings() with the sleek, streamlined Sly Pack"},
			{"Sauce Labs Bike Light", "$9.99", "A red light isn't the desired state in testing but it sure helps when riding your bike at night"},
			{"Sauce Labs Bolt T-Shirt", "$15.99", "Get your testing superhero on with the Sauce Labs bolt T-shirt"},
			{"Sauce Labs Fleece Jacket", "$49.99", "It's not every day that you come across a midweight quarter-zip fleece jacket"}
		};
	}
	
	@Test(dataProvider = "productTestdata")
	public void getProductDetailsTest(String name, String price, String desc) {
		Map<String, String> productMap = cartPage.getProductDetails(name);
		Assert.assertEquals(productMap.get("Product name"), name);
		Assert.assertEquals(productMap.get("Product price"), price);
		Assert.assertTrue(productMap.get("Product desc").contains(desc));
	}

	@Test
	public void removeAnItemAndVerifyCartBadgeCountTest() {
		List<String> products = new ArrayList<String>(Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"));
		cartPage.addListOfProducts(products);
		int count = cartPage.removeAnItemAndVerifyCartBadgeCount("Sauce Labs Backpack");
		Assert.assertEquals(count, inventoryPage.getShoppingCartItemCount());
	}
	
	@Test
	public void removeAllItemsAndVerifyCartPageTest() {
		List<String> products = new ArrayList<String>(Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"));		
		Assert.assertTrue(cartPage.removeAllItemsAndVerifyCartPage(products));
	}
}
