package com.qa.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.utils.ElementUtil;

public class CheckoutCompletePage {

	private WebDriver driver;
	private ElementUtil util;
	
	private By backHomeBtn = By.id("back-to-products");
	private By completeHeader = By.className("complete-header");
	private By completeText = By.className("complete-text");
	private By image = By.className("pony_express");
	
	private By productsPageTitle = By.className("title");
	private By burgerMenuBtn = By.id("react-burger-menu-btn");
	private By logoutLink = By.linkText("Logout");
	
	public CheckoutCompletePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public String getPageUrl() {
		return util.getPageURL();
	}
	
	public String getPageTitle() {
		return util.getPageTitle();
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
		return util.doGetText(productsPageTitle);
	}
	
	public void clickOnLogoutBtn() {
		util.doClick(burgerMenuBtn);
		util.doClick(logoutLink);
	}
	
}
