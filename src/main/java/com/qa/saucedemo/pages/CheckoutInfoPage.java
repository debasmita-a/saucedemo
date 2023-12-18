package com.qa.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.utils.ElementUtil;

public class CheckoutInfoPage {

	private WebDriver driver;
	private ElementUtil util;

	private By cancelBtn = By.id("cancel");
	private By continueBtn = By.id("continue");
	private By firstname = By.id("first-name");
	private By lastname = By.id("last-name");
	private By postalcode = By.id("postal-code");
	private By errorMsg = By.xpath("//h3[@data-test='error']");
	private By cartsPageTitle = By.className("title");
	private By checkoutInfoTitle = By.className("title");

	public CheckoutInfoPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public boolean setflagValue(String firstName, String lastName, String zipcode) {
		if (firstName != null && lastName != null && zipcode != null) {
			return true;
		}
		return false;
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
	
	public String fillInformationWithoutZipcode(String firstName, String lastName) {
		util.doSendKeys(firstname, firstName);
		util.doSendKeys(lastname, lastName);
		//util.doSendKeys(postalcode, zipcode);
		util.doClick(continueBtn);
		return util.doGetAttributeValue(errorMsg, "innerText");
	}
	
	public String fillInformationWithoutLn(String firstName, String zipcode) {
		util.doSendKeys(firstname, firstName);
		//util.doSendKeys(lastname, lastName);
		util.doSendKeys(postalcode, zipcode);
		util.doClick(continueBtn);
		return util.doGetAttributeValue(errorMsg, "innerText");
	}
	
	public String fillInformationWithoutFn(String lastName, String zipcode) {
		//util.doSendKeys(firstname, firstName);
		util.doSendKeys(lastname, lastName);
		util.doSendKeys(postalcode, zipcode);
		util.doClick(continueBtn);
		return util.doGetAttributeValue(errorMsg, "innerText");
	}
	
	public CheckoutOverviewPage clickOnContinue(String firstName, String lastName, String zipcode) {
		util.doSendKeys(firstname, firstName);
		util.doSendKeys(lastname, lastName);
		util.doSendKeys(postalcode, zipcode);
		if(setflagValue(firstName, lastName, zipcode)) {
			util.doClick(continueBtn);
		}
		return new CheckoutOverviewPage(driver);
	}

}
