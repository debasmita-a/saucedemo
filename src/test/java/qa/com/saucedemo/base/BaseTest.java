package qa.com.saucedemo.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.saucedemo.factory.DriverFactory;
import com.qa.saucedemo.pages.InventoryPage;
import com.qa.saucedemo.pages.LoginPage;
import com.qa.saucedemo.pages.ProductCartPage;

public class BaseTest {

	protected DriverFactory df;
	protected WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected InventoryPage inventoryPage;
	protected ProductCartPage cartPage;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@AfterTest
	public void teardown() {
		//driver.quit();
	}
}
