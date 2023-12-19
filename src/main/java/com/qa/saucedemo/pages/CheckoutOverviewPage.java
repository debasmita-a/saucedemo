package com.qa.saucedemo.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.saucedemo.utils.ElementUtil;

public class CheckoutOverviewPage {

	private WebDriver driver;
	private ElementUtil util;
	private Map<String, String> priceTotalInfo = null;

	private By pageTitle = By.className("title");
	private By checkoutInfoTitle = By.className("title");
	private By productName = By.className("inventory_item_name");
	private By productPrice = By.className("inventory_item_price");
	private By summaryInfo = By.className("summary_info");
	private By cancelBtn = By.id("cancel");
	private By finishBtn = By.id("finish");
	private By summaryInfoLabel = By.className("summary_info_label");
	private By summaryInfoValue = By.className("summary_value_label");
	private By summaryPriceSubTotalLabel = By.className("summary_subtotal_label");
	private By taxLabel = By.className("summary_tax_label");

	public CheckoutOverviewPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
		// priceTotalInfo = new HashMap<String, String>();
	}

	public void getPriceMetadata() {
		Map<String, String> priceMap = new HashMap<String, String>();
		String priceMetadata = util.doGetText(summaryPriceSubTotalLabel);
		String pricedata[] = priceMetadata.split(":");
		priceMap.put(pricedata[0].trim(), pricedata[1].trim());
		float itemPrice = Float.parseFloat(pricedata[1].replace("$", "").trim());
		String taxMetadata = util.doGetText(taxLabel);
		String taxdata[] = taxMetadata.split(":");
		float tax = Float.parseFloat(taxdata[1].replace("$", "").trim());
		priceMap.put(taxdata[0].trim(), taxdata[1].trim());
		System.out.println("Price details : " + priceMap);
	}

	public void getPriceTotal() {

	}

	public void getItemOrderMetadata() {
		Map<String, String> orderMap = new HashMap<String, String>();
		List<WebElement> allLabels = util.getElements(summaryInfoLabel);
		List<WebElement> allValues = util.getElements(summaryInfoValue);
		List<String> keys = new ArrayList<String>();
		for(WebElement e : allLabels) {
			keys.add(e.getText());
		}
		List<String> values = new ArrayList<String>();
		for(WebElement e : allValues) {
			values.add(e.getText());
		}
		System.out.println(keys);
		System.out.println(values);
	}

	public String getPageTitle() {
		return util.getPageTitle();
	}

	public String getPageUrl() {
		return util.getPageURL();
	}

	public String clickOnCancelBtn() {
		util.doClick(cancelBtn);
		return util.doGetText(checkoutInfoTitle);
	}

	public CheckoutCompletePage clickOnFinishBtn() {
		util.doClick(finishBtn);
		return new CheckoutCompletePage(driver);
	}

}
