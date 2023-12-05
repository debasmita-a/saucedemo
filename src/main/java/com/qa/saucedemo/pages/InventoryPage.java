package com.qa.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.saucedemo.utils.ElementUtil;

public class InventoryPage {

	private WebDriver driver;
	private ElementUtil util;
	private List<String> sortingOptions = null;
	private List<String> productNames = null;
	private static int cartItemCount = 0;

	private By appLogo = By.className("app_logo");
	private By allProducts = By.xpath("//div[@class='inventory_item_name ']");
	private By shoppingCartLink = By.id("shopping_cart_container");
	private By menuBtn = By.id("react-burger-menu-btn");
	private By menuOptions = By.xpath("//nav/a");
	private By filters = By.className("product_sort_container");
	private By footer = By.className("footer_copy");
	
	public InventoryPage(WebDriver driver){
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	/*
	 * 
	 * To verify that::
	 * 1.Logo is visible on landing page
	 * 2.All products are displayed
	 * 3.Product count be correct
	 * 4.Apply a filter and verify the sorting
	 * 5.Should be able to add a product to cart
	 * 6.Should be able to remove a product from cart
	 * 7.Cart link is available
	 * 8.Number of items displayed on cart should match number of 
	 * items added
	 * 9.Logout link is available
	 * 10.Log back in and verify the cart details are preserved
	 * 11.Verify all filter options available
	 * 12.Verify all links available -- burger menu
	 * 13.Verify all footer links
	 * 14.Verify a particular product is available in the list of products
	 * 
	 * 
	 * Navigations
	 * Click on cart--> Check out page
	 * Click on a product name--> Product details page
	 * 
	 */
	
	public boolean isAppLogoDisplayed() {
		return util.isElementDisplayed(appLogo);
	}
	
	public int getProductCount() {
		return util.getElements(allProducts).size();
	}
	
	public List<String> getAllSortingOptions() {
		List<WebElement> sortingOptionsList =  util.getDropdownOptions(filters);
		for(WebElement e : sortingOptionsList) {
			sortingOptions.add(e.getText());
		}		
		return sortingOptions;
	}
	
	public boolean isProductAvailable(String productname) {
		List<WebElement> allProductList = util.getElements(allProducts);
		for(WebElement e : allProductList) {
			if(e.getText().contains(productname)) {	
				return true;
			}
		}
		return false;
	}
	
	public boolean isCartLinkAvaiable() {
		return util.isElementDisplayed(shoppingCartLink);
	}
	
	public boolean isLogoutLinkAvailable() {
		util.doClick(menuBtn);
		List<WebElement> allNavMenu = util.getElements(menuOptions);
		for(WebElement e : allNavMenu) {
			if(e.getText().equals("Logout")) {
				return true;
			}
		}
		return false;
	}
	
	public String getFooterText() {
		return util.doGetAttributeValue(footer, "innerText");
	}
	
	public List<String> productListWithSortOption(String sortOption) {
		util.doClick(filters);
		util.doSelectWithVisibleText(filters, sortOption);
		List<WebElement> productList = util.getElements(allProducts);
		for(WebElement e: productList) {
			productNames.add(e.getText());
		}
		return productNames;
	}
	
	public void addAProductToCart(String productname) {
		util.doClick(util.getProductAddToCartID(productname));
	}
	
	
}
