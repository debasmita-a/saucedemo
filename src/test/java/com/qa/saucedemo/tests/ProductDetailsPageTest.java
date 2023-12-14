package com.qa.saucedemo.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.com.saucedemo.base.BaseTest;

public class ProductDetailsPageTest extends BaseTest{

	@DataProvider
	public Object[][] getProductDetailsTestdata(){
		return new Object[][] {
			{"Sauce Labs Backpack"},
			{"Sauce Labs Bike Light"},
			{"Sauce Labs Bolt T-Shirt"},
			{"Sauce Labs Fleece Jacket"}
		};
	}
	
	@BeforeClass
	public void productDetailsPageSetup() {
		inventoryPage = loginPage.navigateToInventoryPage(prop.getProperty("username"), prop.getProperty("password"));			                      
	}
	
	@Test(dataProvider = "getProductDetailsTestdata")
	public void getAllProductDetailsTest(String productname) {
		productDetailsPage = inventoryPage.navigateToProductDetailsPage(productname);
		productDetailsPage.getAllProductDetails(productname);
	}
}
