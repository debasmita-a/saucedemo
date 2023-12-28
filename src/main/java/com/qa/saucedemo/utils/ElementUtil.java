package com.qa.saucedemo.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;
	private Select select;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	//****************** element utils ***************************
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public String doGetAttributeValue(By locator, String attribute) {
		return getElement(locator).getAttribute(attribute);
	}
	
	public boolean isElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public List<WebElement> getDropdownOptions(By locator) {
		select = new Select(getElement(locator));
		return select.getOptions();
	}
	
	public void doSelectByValue(By locator, String value) {
		select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public void doSelectWithVisibleText(By locator, String value) {
		select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}
	
	//**************browser utils**********************************
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
	
	//******************app utils*********************************
	public String productNameToLowerCase(String productName) {
		return productName.toLowerCase().replace(" ", "-").trim();
	}
	
	public By getProductAddToCartID(String productName) {
		//add-to-cart-sauce-labs-backpack 
		return By.id("add-to-cart-" + productNameToLowerCase(productName));
	}
	
	public By getProductRemoveId(String productName) {
		//remove-sauce-labs-backpack
		return By.id("remove-" + productNameToLowerCase(productName));
	}
	
	//*******************Actions class utilities****************
	public void doActionClick(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).click().build().perform();
	}
	
	//*****************Wait utilities*******************
	public WebElement waitForElementToBeClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));	
	}
	
	public WebElement waitForElementToBeVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
