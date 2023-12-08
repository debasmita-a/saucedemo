package com.qa.saucedemo.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.saucedemo.exceptions.FrameworkException;

public class DriverFactory {

	private WebDriver driver;
	private Properties prop;
	
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			System.out.println("Launching Chrome..");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.out.println("Launching Firefox..");
			driver = new FirefoxDriver();
			break;
		case "edge":
			System.out.println("Launching Edge..");
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Please provide correct browser name..");
			new FrameworkException("CONFIGURATIONEXCEPTION");
		}
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		return driver;
	}
	
	public Properties initProp() {
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
}
