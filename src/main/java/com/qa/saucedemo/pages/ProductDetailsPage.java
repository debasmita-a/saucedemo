package com.qa.saucedemo.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.utils.ElementUtil;

public class ProductDetailsPage {

	private WebDriver driver;
	private ElementUtil util;
	private Map<String, String> productMap = null;
	
	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		productMap = new HashMap<String, String>();
	}
	
	public String getProductName(String productname) {
		////div[@class='inventory_item_name ' and contains(text(),'Sauce Labs Bolt T-Shirt')]
		String productXpath = "//div[@class='inventory_item_name ' and contains(text(),'" + productname + "')]";
		String productName = util.doGetAttributeValue(By.xpath(productXpath), "innerText");
		return productName;
	}
	public String getProductPrice(String productname) {
		////div[text()='Sauce Labs Bolt T-Shirt']/../../following-sibling::div/div[@class='inventory_item_price']
		String productPriceXpath = "//div[text()='" + productname + "']/../../following-sibling::div/div[@class='inventory_item_price']";
		String productPrice = util.doGetAttributeValue(By.xpath(productPriceXpath), "innerText");
		return productPrice;
	}
	
	public String getProductDescription(String productname) {
		////div[contains(text(),'Sauce Labs Bolt T-Shirt')]/parent::a/following-sibling::div[@class='inventory_item_desc']
		String productDescXpath = "//div[contains(text(),'" + productname + "')]/parent::a/following-sibling::div[@class='inventory_item_desc']";
		String productDesc = util.doGetAttributeValue(By.xpath(productDescXpath), "innerText");
		return productDesc;
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
	public Map<String, String> getAllProductDetails(String productname) {
		productMap.put("Product Name", getProductName(productname));
		productMap.put("Price", getProductPrice(productname));
		productMap.put("Description", getProductDescription(productname));
		return productMap;
	}
	
}
