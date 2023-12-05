package com.qa.saucedemo.pages;

import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.utils.ElementUtil;

public class ProductCartPage {

	private WebDriver driver;
	private ElementUtil util;
	
	public ProductCartPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
}
