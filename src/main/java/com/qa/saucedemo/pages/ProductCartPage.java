package com.qa.saucedemo.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.utils.ElementUtil;

public class ProductCartPage {

	private WebDriver driver;
	private ElementUtil util;

	private By continueShoppingBtn = By.id("continue-shopping");
	private By checkoutBtn = By.id("checkout");
	private By cartProductCount = By.className("cart_item");
	private By cartBadgeCount = By.className("shopping_cart_badge");
	private By productsPageTitle = By.className("title");
	private By shoppingCartLink = By.id("shopping_cart_container");
	private By allProducts = By.xpath("//div[@class='inventory_item_name']");
	private By cartsPageTitle = By.className("title");

	private static int count = 0;

	public ProductCartPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	/*
	 * 
	 * 1.verify that number of items added matches the number of cart items 2.verify
	 * that products added matches the products available in the cart 3.verify that
	 * user is able to remove a product 4.verify checkout button is available -->
	 * should land on check out page 5.verify that continue shopping button is
	 * available --> should land on inventory page 6.remove all items from the cart
	 * and verify that no item is available 7.remove all items from cart and verify
	 * that checkout button should be disabled? 8.remove items and verify the cart
	 * badge count 9.verify the product details --> name, price and desc -- same as
	 * pt 2 10.Logout and log back in --verify all items are available in cart
	 * 
	 */

	public boolean isContinueShoppingBtnAvailable() {
		return util.isElementDisplayed(continueShoppingBtn);
	}

	public boolean isCheckoutBtnAvailable() {
		return util.isElementDisplayed(checkoutBtn);
	}

	public int getCartItemCount(String productname) {
		clickOnContinueShoppingBtn();
		addAProductToCart(productname);
		clickOnCartLink();
		return util.getElements(cartProductCount).size();
	}

	public String getCartPageTitle() {
		return util.doGetText(cartsPageTitle);
	}

	public String getCartPageUrl() {
		return util.getPageURL();
	}

	public String clickOnContinueShoppingBtn() {
		util.doClick(continueShoppingBtn);
		return util.doGetText(productsPageTitle);
	}

	public void clickOnCartLink() {
		util.doClick(shoppingCartLink);
	}

	public Map<String, String> getProductDetails(String productname) {
		// product details should match with the ones added to cart
		// step - 1: add products to cart -- Test class
		// step - 2: verify the product name

		clickOnContinueShoppingBtn();
		addAProductToCart(productname);
		clickOnCartLink();
		count++;

		Map<String, String> productMap = new HashMap<String, String>();
		productMap.put("Product name", util
				.doGetAttributeValue(By.xpath("(//div[@class='inventory_item_name'])[" + count + "]"), "innerText"));
		productMap.put("Product price", util
				.doGetAttributeValue(By.xpath("(//div[@class='inventory_item_price'])[" + count + "]"), "innerText"));
		productMap.put("Product desc", util
				.doGetAttributeValue(By.xpath("(//div[@class='inventory_item_desc'])[" + count + "]"), "innerText"));
		return productMap;
	}

	public void addAProductToCart(String productname) {
		util.doClick(util.getProductAddToCartID(productname));
	}

	public void addListOfProducts(List<String> products) {
		util.doClick(continueShoppingBtn);
		for (String product : products) {
			util.doClick(util.getProductAddToCartID(product));
		}
		util.doClick(shoppingCartLink);
	}

	public int removeAnItemAndVerifyCartBadgeCount(String productname) {
		// add a products then remove
		if (util.isElementDisplayed(util.getProductRemoveId(productname))) {
			util.doClick(util.getProductRemoveId(productname));
		} else {
			System.out.println("Product not added not cart.");
		}
		return Integer.parseInt(util.doGetText(cartBadgeCount));
	}

	public boolean removeAllItemsAndVerifyCartPage(List<String> products) {
		addListOfProducts(products);
		for (String product : products) {
			util.doClick(util.getProductRemoveId(product));
		}
		if (util.isElementDisplayed(checkoutBtn)) {
			return false;
		}
		return true;
	}

	public CheckoutInfoPage navigateToCheckoutPage(List<String> products) {
		addListOfProducts(products);
		if (util.getElements(allProducts).size() > 0) {
			util.doClick(checkoutBtn);
			return new CheckoutInfoPage(driver);
		} else {
			return null;
		}
	}

}
