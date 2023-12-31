package com.qa.saucedemo.constants;

import java.util.Arrays;
import java.util.List;

public class FrameworkConstants {

	public static final String INVENTORY_PAGE_URL = "inventory.html";
	public static final String CART_PAGE_URL = "cart.html";
	public static final String CHECKOUT_STEP1_PAGE_URL = "checkout-step-one.html";
	public static final String CHECKOUT_STEP2_PAGE_URL = "checkout-step-two.html";
	public static final String CHECKOUT_COMPLETE_PAGE_URL = "checkout-complete.html";
	
	public static final List<String> SORTING_OPTIONS = Arrays.asList
			("Name (A to Z)", 
			"Name (Z to A)", 
			"Price (low to high)", 
			"Price (high to low)");	
	public static final int PRODUCT_COUNT = 6;
	public static final String FOOTER_TEXT = "� 2023 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy";
	
	public static final String CART_PAGE_HEADER = "Your Cart";
	public static final String INVENTORY_PAGE_HEADER = "Products";
	public static final String CHECKOUT_STEP1_PAGE_HEADER = "Checkout: Your Information";
	public static final String CHECKOUT_STEP2_PAGE_HEADER = "Checkout: Overview";
	public static final String CHECKOUT_COMPLETE_PAGE_HEADER = "Checkout: Complete!";
	
	public static final String POSTAL_CODE_ERROR = "Error: Postal Code is required";
	public static final String FIRST_NAME_ERROR = "Error: First Name is required";
	public static final String LAST_NAME_ERROR = "Error: Last Name is required";
	
	public static final String PAYMENT_INFO_VALUE_LABEL = "SauceCard #31337";
	public static final String SHIPPING_INFO_VALUE_LABEL = "Free Pony Express Delivery!";
	
	public static final String ORDER_COMPLETION_HEADER = "Thank you for your order!";
	public static final String ORDER_COMPLETION_TEXT = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
}
