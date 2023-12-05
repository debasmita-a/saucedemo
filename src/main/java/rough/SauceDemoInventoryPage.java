package rough;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.qa.saucedemo.utils.ElementUtil;

public class SauceDemoInventoryPage {
	
	private static By username = By.id("user-name");
	private static By password = By.id("password");
	private static By loginBtn = By.id("login-button");
	private static WebDriver driver;
	private static ElementUtil util;
	private static Map<String, String> productMap = null;
	private static List<String> allProduct = null;
	private static List<String> allFilters = null;
	
	private By allProducts = By.xpath("//div[@class='inventory_item_name ']");
	private By shoppingCartLink = By.id("shopping_cart_container");
	private By itemPrice = By.className("inventory_item_price");
	private static By allProductNames = By.xpath("//div[@class='inventory_item_name ']");
	private static By filters = By.className("product_sort_container");

	public SauceDemoInventoryPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
		productMap = new HashMap<String, String>();
		allProduct = new ArrayList<String>();
		allFilters = new ArrayList<String>();
	}
	public static void main(String[] args) {
		SauceDemoInventoryPage sauceObj = new SauceDemoInventoryPage(new ChromeDriver());
		driver.get("https://www.saucedemo.com/");
		String url = sauceObj.doLogin("standard_user", "secret_sauce");
		
		System.out.println(url);
		
		productMap = allProductDetails("Sauce Labs Onesie");		
		System.out.println(productMap);
		
		allProduct = getAllProductNames(allProductNames);
		System.out.println(allProduct);
		
		allFilters = productListFilters(filters);
		System.out.println(allFilters);
	}
	
	public static Map<String, String> allProductDetails(String productName) {
		
		productMap.put("Product Name", productName);
		////div[text()='Sauce Labs Bolt T-Shirt']/../../following-sibling::div/div[@class='inventory_item_price']
		String productPriceXpath = "//div[text()='"+productName+"']/../../following-sibling::div/div[@class='inventory_item_price']";
		String productPrice = util.doGetAttributeValue(By.xpath(productPriceXpath), "innerText");
		productMap.put("Product price", productPrice);
		return productMap;
	}
	
	public static String getProductAddToCartId(String productName) {
		//add-to-cart-sauce-labs-backpack
		String product = productName.toLowerCase().replace(" ", "-");
		String addToCartId = "add-to-cart-" + product;
		return addToCartId;
	}
	
	public static String getProductRemoveId(String productName) {
		//remove-sauce-labs-backpack
		String product = productName.toLowerCase().replace(" ", "-");
		String removeId = "remove-" + product;
		return removeId;
	}
	
	
	public String doLogin(String usernameValue, String passwordValue) {
		util.doSendKeys(username, usernameValue);
		util.doSendKeys(password, passwordValue);
		util.doClick(loginBtn);
		return util.getPageURL();
	}
	
	public static List<String> getAllProductNames(By locator) {		
		List<WebElement> allProductElements = driver.findElements(locator);
		for(WebElement e : allProductElements) {
			allProduct.add(e.getText());
		}	
		return allProduct;
	}
	
	public static List<String> productListFilters(By locator) {
		Select select = new Select(driver.findElement(locator));
		List<WebElement> filtersList = select.getOptions();
		for(WebElement e: filtersList) {
			allFilters.add(e.getText());
		}
		return allFilters;
	}
	
	public static List<String> productSortedList(By productLocator, By filterLocator, String filterName){
		util.doClick(filterLocator);
		Select select = new Select(driver.findElement(filterLocator));
		select.deselectByValue(filterName);
		return getAllProductNames(productLocator);
	}
}
