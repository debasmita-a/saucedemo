package com.qa.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.qa.saucedemo.utils.ElementUtil;

public class CheckoutCompletePage {

	private WebDriver driver;
	private ElementUtil util;
	
	private By backHomeBtn = By.id("back-to-products");
	private By completeHeader = By.className("complete-header");
	private By completeText = By.className("complete-text");
	private By image = By.className("pony_express");
	private By completionPageTitle = By.className("title");
	
	private By productsPageTitle = By.className("title");
	private By burgerMenuBtn = By.id("react-burger-menu-btn");
	//private By logoutLink = By.linkText("Logout");
	private By logoutLink = By.id("logout_sidebar_link");
	private By loginBtn = By.id("login-button");
	
	public CheckoutCompletePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public String getPageUrl() {
		return util.getPageURL();
	}
	
	public String getPageTitle() {
		return util.doGetText(completionPageTitle);
	}
	
	public String getCompletionHeaderMsg() {
		return util.doGetText(completeHeader);
	}
	
	public String getCompletionText() {
		return util.doGetText(completeText);
	}
	
	public boolean isImageAvailable() {
		return util.isElementDisplayed(image);
	}
	
	public String clickOnBackHomeBtn() {
		util.doClick(backHomeBtn);
		String productsTitle = util.doGetText(productsPageTitle);
		driver.navigate().back();
		return productsTitle;
	}
	
	public boolean clickOnLogoutBtn() {
		util.doClick(burgerMenuBtn);
		//util.doClick(logoutLink);
		Actions action = new Actions(driver);
		action.click(util.getElement(logoutLink));
		return util.isElementDisplayed(loginBtn);
	}
	
}
