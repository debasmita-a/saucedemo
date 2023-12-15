package com.qa.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.utils.ElementUtil;

public class CheckoutInfoPage {

	private WebDriver driver;
	private ElementUtil util;
	private static boolean flag = false;

	private By cancelBtn = By.id("cancel");
	private By continueBtn = By.id("continue");
	private By firstname = By.id("first-name");
	private By lastname = By.id("last-name");
	private By postalcode = By.id("postal-code");
	private By errorMsg = By.xpath("//h3/button[@class = 'error-button']");
	private By cartsPageTitle = By.className("title");
	private By checkoutInfoTitle = By.className("title");

	public CheckoutInfoPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public String fillInformation(String firstName, String lastName, String zipcode) {
		if (firstName != null && lastName != null && zipcode != null) {
			util.doSendKeys(firstname, firstName);
			util.doSendKeys(lastname, lastName);
			util.doSendKeys(postalcode, zipcode);
			flag = true;
			return "";
		}
		return util.doGetText(errorMsg);
	}

	public String getCheckoutInfoPageTitle() {
		return util.doGetText(checkoutInfoTitle);
	}

	public String getCheckoutPageUrl() {
		return util.getPageURL();
	}

	public String clickOnCancel() {
		util.doClick(cancelBtn);
		return util.doGetText(cartsPageTitle);
	}

	public CheckoutOverviewPage clickOnContinue(String firstName, String lastName, String zipcode) {
		fillInformation(firstName, lastName, zipcode);
		if(flag == true) {
			util.doClick(continueBtn);
		}
		return new CheckoutOverviewPage(driver);
	}

}
