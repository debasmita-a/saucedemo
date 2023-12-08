package com.qa.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.utils.ElementUtil;

public class ProductCartPage {

	private WebDriver driver;
	private ElementUtil util;
	
	private By continueShoppingBtn = By.id("continue-shopping");
	private By checkoutBtn = By.id("checkout");
	private By cartItemCount = By.className("cart_item");
	
	
	
	public ProductCartPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	/*
	 * 
	 * 1.verify that number of items added matches the number of cart items
	 * 2.verify that products added matches the products available in the cart
	 * 3.verify that user is able to remove a product
	 * 4.verify checkout button is available --> should land on check out page
	 * 5.verify that continue shopping button is available --> should land on inventory page
	 * 6.remove all items from the cart and verify that no item is available
	 * 7.remove all items from cart and verify that checkout button should be disabled?
	 * 8.remove items and verify the cart badge count
	 * 9.verify the product details --> name, price and desc -- same as pt 2
	 * 10.Logout and log back in --verify all items are available in cart
	 * 
	 */
	
	public boolean isContinueShoppingBtnAvailable() {
		return util.isElementDisplayed(continueShoppingBtn);
	}
	
	public boolean isCheckoutBtnAvailable() {
		return util.isElementDisplayed(checkoutBtn);
	}
	
	public int getCartItemCount() {
		return util.getElements(cartItemCount).size();
	}
	
	public void clickOnContinueShoppingBtn() {
		
	}
	
	public void verifyProductDetails() {
		
	}
	
	public void removeAnItemAndVerifyCartBadgeCount() {
		
	}
	
	public void removeAllItemsAndVerifyCartPage() {
		
	}
	
	
}
