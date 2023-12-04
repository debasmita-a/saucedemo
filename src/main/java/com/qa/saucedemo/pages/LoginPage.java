package com.qa.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil util;
	
	private By username = By.id("user-name");
	private By password = By.id("password");
	private By loginBtn = By.id("login-button");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public String doLogin(String usernameValue, String passwordValue) {
		util.doSendKeys(username, usernameValue);
		util.doSendKeys(password, passwordValue);
		util.doClick(loginBtn);
		return util.getPageURL();
	}
	
	public InventoryPage navigateToInventoryPage(String usernameValue, String passwordValue) {
		doLogin(usernameValue, passwordValue);
		return new InventoryPage(driver);
	}
}
