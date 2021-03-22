package com.lufthansa.test;

import java.io.IOException;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.lufthansa.util.LufthansaTestUtil;
import com.lufthansa.base.LufthansaTestBase;
import com.lufthansa.pages.LoginPage;

public class LoginPageTest extends LufthansaTestBase {
	
	LoginPage loginPage;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void SetUp() {
		LaunchBrowser();
		loginPage = new LoginPage();
	}
	
	@Test
	public void CheckLoginPageTitle() {		
		String ActualTitle = loginPage.getTitle();
		System.out.println("Login Page Title: "+ActualTitle);
		String ExpectedTitle = properties.getProperty("LoginPageTitle");
		Assert.assertEquals(ExpectedTitle, ActualTitle, "Login Page Title is displayed Incorrectly");
	}
	
	@Test
	public void Login() throws IOException {
		Map<String, String> map = LufthansaTestUtil.getLufthansaTestData();
		boolean b = loginPage.EnterLoginDetails(map.get("Email_Id"), map.get("Password"));
		Assert.assertTrue(b,"Error Message is not displayed");
	}
	
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}

}
