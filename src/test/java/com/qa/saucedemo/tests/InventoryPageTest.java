package com.qa.saucedemo.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.saucedemo.constants.FrameworkConstants;

import qa.com.saucedemo.base.BaseTest;

public class InventoryPageTest extends BaseTest{
	
	@BeforeClass
	public void inventoryPageSetup() {
		inventoryPage = loginPage.navigateToInventoryPage(prop.getProperty("username").trim(), prop.getProperty("password").trim());		
	}
	
	@Test
	public void isAppLogoDisplayedTest() {
		Assert.assertTrue(inventoryPage.isAppLogoDisplayed());
	}
	
	@Test
	public void getProductCountTest() {
		Assert.assertEquals(inventoryPage.getProductCount(), FrameworkConstants.PRODUCT_COUNT);
	}
	
	@Test
	public void getAllSortingOptionsTest() {
		Assert.assertEquals(inventoryPage.getAllSortingOptions(), FrameworkConstants.SORTING_OPTIONS);
	}
	
	@DataProvider
	public Object[][] productData(){
		return new Object[][] {
				{"Sauce Labs Backpack"},
				{"Sauce Labs Bike Light"},
				{"Sauce Labs Bolt T-Shirt"},
				{"Sauce Labs Fleece Jacket"}
		};
	}
	
	@Test(dataProvider = "productData")
	public void isProductAvailableTest(String productname) {
		Assert.assertTrue(inventoryPage.isProductAvailable(productname));
	}
	
	@Test
	public void isCartLinkAvaiableTest() {
		Assert.assertTrue(inventoryPage.isCartLinkAvaiable());
	}
	
	@Test
	public void isLogoutLinkAvailableTest() {
		Assert.assertTrue(inventoryPage.isLogoutLinkAvailable());
	}
	
	@Test
	public void getFooterTextTest() {
		Assert.assertEquals(inventoryPage.getFooterText(), FrameworkConstants.FOOTER_TEXT);
	}
	
	@Test
	public void getInventoryPageURLTest() {
		Assert.assertTrue(inventoryPage.getInventoryPageURL().contains(FrameworkConstants.INVENTORY_PAGE_URL));;
	}
	
	@DataProvider
	public Object[][] getProductSortedListByName(){
		return new Object[][] {
			{"Name (A to Z)"},
			{"Name (Z to A)"}
		};
	}
	
	@Test(dataProvider = "getProductSortedListByName")
	public void productSortByNameTest(String sortingOption) {
		Assert.assertTrue(inventoryPage.productSortByName(sortingOption));
	}
	
	@DataProvider
	public Object[][] getProductSortedListByPrice(){
		return new Object[][] {
			{"Price (low to high)"},
			{"Price (high to low)"}
		};
	}
	
	@Test(dataProvider = "getProductSortedListByPrice")
	public void getAllProductPricesTest(String sortingOption) {
		Assert.assertTrue(inventoryPage.productSortByPrice(sortingOption));
	}
	
	@DataProvider
	public Object[][] cartTestData1(){
		return new Object[][] {
			{"Sauce Labs Onesie", 1},
			{"Sauce Labs Bolt T-Shirt", 2},
			{"Test.allTheThings() T-Shirt (Red)", 3}
		};
	}
	
	@Test(dataProvider = "cartTestData1")
	public void addAProductToCartTest(String product, int itemCount) {
		Assert.assertTrue(inventoryPage.addAProductToCart(product));
		Assert.assertEquals(inventoryPage.getShoppingCartItemCount(), itemCount);
	}
	
	@DataProvider
	public Object[][] cartTestData2(){
		return new Object[][] {
			{"Sauce Labs Onesie"},
			{"Sauce Labs Bolt T-Shirt"},
			{"Test.allTheThings() T-Shirt (Red)"}
		};
	}
	
	@Test(dataProvider = "cartTestData2")
	public void removeAProductFromCartTest(String product) {
		inventoryPage.addAProductToCart(product);
		Assert.assertTrue(inventoryPage.removeAProductFromCart(product));
		Assert.assertEquals(inventoryPage.getShoppingCartItemCount(), 0);
	}
	
}
