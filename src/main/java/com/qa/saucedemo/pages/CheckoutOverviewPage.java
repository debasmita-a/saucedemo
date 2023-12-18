package com.qa.saucedemo.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
	private By summaryInfoLabel = By.className("summary_value_label");
	private By summaryInfoValue = By.className("summary_value_label");
	private By summaryPriceSubTotalLabel = By.className("summary_subtotal_label");
	private By taxLabel = By.className("summary_tax_label");
	private By totalPriceLabel = By.className("summary_info_label summary_total_label");
	
	public CheckoutOverviewPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
		priceTotalInfo = new HashMap<String, String>();
	}
	
	public void extractPriceValue() {
		String priceInfo = util.doGetAttributeValue(summaryPriceSubTotalLabel, "innerText");
		System.out.println(priceInfo);
	}
	public void getPriceTotal() {
		
	}
	
	
}
