package com.qa.saucedemo.pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.utils.ElementUtil;

public class InventoryPage {

	private WebDriver driver;
	private ElementUtil util;
	private Map<String, String> productMap = null;
	
	private By allProducts = By.xpath("//div[@class='inventory_item_name ']");
	private By shoppingCartLink = By.id("shopping_cart_container");
	private By itemPrice = By.className("inventory_item_price");
	
	public InventoryPage(WebDriver driver){
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	/*
	 * In this method, we will extract the below product details
	 * Product name
	 * Product price
	 * Product description
	 * 
	 * @params productName
	 * 
	 * @returns a Map<String, String>
	 */
	public void allProductDetails(String productName) {
		
		productMap.put("Product Name", productName);
		////div[text()='Sauce Labs Bolt T-Shirt']/../../following-sibling::div/div[@class='inventory_item_price']
		String productPriceXpath = "//div[text()='"+productName+"']/../../following-sibling::div/div[@class='inventory_item_price']";
		String productPrice = util.doGetAttributeValue(By.xpath(productPriceXpath), "innerText");
	}
	
	public String getProductAddToCartId(String productName) {
		//add-to-cart-sauce-labs-backpack
		String product = productName.toLowerCase().replace(" ", "-");
		String addToCartId = "add-to-cart-" + product;
		return addToCartId;
	}
	
	public String getProductRemoveId(String productName) {
		//remove-sauce-labs-backpack
		String product = productName.toLowerCase().replace(" ", "-");
		String removeId = "remove-" + product;
		return removeId;
	}
	
}
