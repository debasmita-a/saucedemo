package com.qa.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.saucedemo.constants.FrameworkConstants;

import qa.com.saucedemo.base.BaseTest;

public class LoginPageTest extends BaseTest{

	@Test
	public void doLoginTest() {
		String actualURL = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(actualURL.contains(FrameworkConstants.INVENTORY_PAGE_URL));
	}
}
