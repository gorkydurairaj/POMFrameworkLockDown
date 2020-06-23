package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.basepage.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.Credentials;

public class LoginPageTest {
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	Credentials userCred;
	
	@BeforeTest
	public void setUp()
	{
		basePage = new BasePage();
		prop = basePage.init_Properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_Driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"),prop.getProperty("password"));

	}
	
	@Test(priority =1)
	public void verifyLoginPageTitle() throws InterruptedException
	{
//		Thread.sleep(6000);
	String title = loginPage.getLoginPageTitle();
		System.out.println("Login page Title is : " +title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	@Test(priority=2)
	public void verifySignUpLinkTest()
	{
		Assert.assertTrue(loginPage.getSignUpLink());
		
	}
	@Test(priority=3)
	public void LoginTest()
	{
		HomePage homePage = loginPage.doLogin(userCred);
		String header = homePage.getHomePageHeaderName();
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}
	
	@DataProvider
	public Object[][] getInvalidCredentialsData()
	{
		Object data [] [] = { {"test100@gmail.com", "test@123"},
				                                          {"test200!gmail.com", "test@5121"},   
				                                          {"", "test@123"},
				                                          {"test300#gmail.com", ""},
				                                          {"test400$gmail.com", "test@123"},
				                                          };
		return data;
	}
	
	@Test(priority =4,dataProvider="getInvalidCredentialsData", enabled = false)
	public void InvalidLoginTest(String username,String pwd)
	{
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		HomePage homePage = loginPage.doLogin(userCred);
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
