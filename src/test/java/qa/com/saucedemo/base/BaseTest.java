package qa.com.saucedemo.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import com.qa.saucedemo.factory.DriverFactory;
import com.qa.saucedemo.pages.LoginPage;

public class BaseTest {

	private DriverFactory df;
	private WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	
	@BeforeClass
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}
}
