package com.qa.saucedemo.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
	private List<Float> productPrices = null;
	private static int cartItemCount = 0;

	private By appLogo = By.className("app_logo");
	private By allProducts = By.xpath("//div[@class='inventory_item_name ']");
	private By allProductPrice = By.className("inventory_item_price");
	private By shoppingCartLink = By.id("shopping_cart_container");
	private By cartBadge = By.className("shopping_cart_badge");
	private By menuBtn = By.id("react-burger-menu-btn");
	private By menuOptions = By.xpath("//nav/a");
	private By menuCrossBtn = By.id("react-burger-cross-btn");
	private By filters = By.className("product_sort_container");
	private By footer = By.className("footer_copy");

	public InventoryPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	/*
	 * 
	 * To verify that:: 1.Logo is visible on landing page 2.All products are
	 * displayed 3.Product count be correct 4.Apply a filter and verify the sorting
	 * 5.Should be able to add a product to cart 6.Should be able to remove a
	 * product from cart 7.Cart link is available 8.Number of items displayed on
	 * cart should match number of items added 9.Logout link is available 10.Log
	 * back in and verify the cart details are preserved 11.Verify all filter
	 * options available 12.Verify all links available -- burger menu 13.Verify all
	 * footer links 14.Verify a particular product is available in the list of
	 * products
	 * 
	 * 
	 * Navigations Click on cart--> Check out page Click on a product name-->
	 * Product details page
	 * 
	 */

	public boolean isAppLogoDisplayed() {
		return util.isElementDisplayed(appLogo);
	}

	public int getProductCount() {
		return util.getElements(allProducts).size();
	}

	public List<String> getAllSortingOptions() {
		List<WebElement> sortingOptionsList = util.getDropdownOptions(filters);
		sortingOptions = new ArrayList<String>();
		for (WebElement e : sortingOptionsList) {
			sortingOptions.add(e.getText());
		}
		return sortingOptions;
	}

	public List<String> getAllProductNames() {
		List<WebElement> allProductList = util.getElements(allProducts);
		productNames = new ArrayList<String>();
		for (WebElement e : allProductList) {
			productNames.add(e.getText());
		}
		return productNames;
	}

	public List<Float> getAllProductPrices() {
		List<WebElement> allProductPriceList = util.getElements(allProductPrice);
		productPrices = new ArrayList<Float>();
		for (WebElement e : allProductPriceList) {
			productPrices.add(Float.parseFloat(e.getAttribute("innerText").replace("$", "").replace("\n", "").trim()));
		}
		return productPrices;
	}

	public boolean productSortByName(String sortOption) { // use sorting methods from List
		util.doClick(filters);
		util.doSelectWithVisibleText(filters, sortOption);
		productNames = getAllProductNames();
		List<String> productNamesSorted = new ArrayList<>(productNames);

		if (sortOption.equals("Name (A to Z)")) {
			productNamesSorted.sort(Comparator.naturalOrder());
			return productNames.equals(productNamesSorted);
		} else if (sortOption.equals("Name (Z to A)")) {
			productNamesSorted.sort(Comparator.reverseOrder());
			return productNames.equals(productNamesSorted);
		} else {
			return false;
		}
	}

	public boolean productSortByPrice(String sortOption) {
		util.doClick(filters);
		util.doSelectWithVisibleText(filters, sortOption);
		productPrices = getAllProductPrices();
		List<Float> productPricesSorted = new ArrayList<>(productPrices);

		if (sortOption.equals("Price (low to high)")) {
			productPricesSorted.sort(Comparator.naturalOrder());
			return productPrices.equals(productPricesSorted);
		} else if (sortOption.equals("Price (high to low)")) {
			productPricesSorted.sort(Comparator.reverseOrder());
			return productPrices.equals(productPricesSorted);
		} else {
			return false;
		}
	}

	public boolean isProductAvailable(String productname) {
		List<WebElement> allProductList = util.getElements(allProducts);
		for (WebElement e : allProductList) {
			if (e.getText().contains(productname)) {
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
		for (WebElement e : allNavMenu) {
			if (e.getText().equals("Logout")) {
				util.doClick(menuCrossBtn);
				return true;
			}
		}
		//util.doClick(menuCrossBtn);
		return false;
	}

	public String getFooterText() {
		return util.doGetAttributeValue(footer, "innerText");
	}

	public String getInventoryPageURL() {
		return util.getPageURL();
	}
//**************** add / remove items to cart ************************************
	public int getShoppingCartItemCount() {
		if(util.getElements(cartBadge).size()>0) {
			cartItemCount = Integer.parseInt(util.doGetText(cartBadge));
			return cartItemCount;
		}else {
			return 0;
		}	
	}
	
	public boolean addAProductToCart(String productname) {// if already added then it can't be added
		if (util.isElementDisplayed(util.getProductAddToCartID(productname))) {
			util.doClick(util.getProductAddToCartID(productname));
			cartItemCount++;
			return util.isElementDisplayed(util.getProductRemoveId(productname));
		} else {
			System.out.println("Product is already added to Cart.");
			return false;
		}
	}

	public boolean removeAProductFromCart(String productname) { // if already added then it can't be added
		if (util.isElementDisplayed(util.getProductRemoveId(productname))) {
			util.doClick(util.getProductRemoveId(productname));
			cartItemCount--;
			return util.isElementDisplayed(util.getProductAddToCartID(productname));
		} else {
			System.out.println("Product has not been added to Cart.");
			return false;
		}
	}

//**********************navigate to other pages ****************************************
	public ProductCartPage navigateToCartPage() {
		util.doClick(shoppingCartLink);
		return new ProductCartPage(driver);
	}

	public ProductDetailsPage navigateToProductDetailsPage(String productname) {
		// div[contains(text(),'Sauce Labs Onesie')]
		String productXpath = "//div[contains(text(),'" + productname + "')]";
		util.doClick(By.xpath(productXpath));
		return new ProductDetailsPage(driver);
	}
}
