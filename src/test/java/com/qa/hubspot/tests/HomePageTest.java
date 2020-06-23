package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.basepage.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Credentials;

public class HomePageTest {
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	@BeforeTest
	public void setUp() throws InterruptedException
	{
		basePage = new BasePage();
		prop = basePage.init_Properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_Driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
//		Thread.sleep(5000);
	}
	
	@Test(priority = 1)
	public void verifyHomePageTitleTest()
	{
		String title  = homePage.getHomePageTitle();
		System.out.println("HomePage Title is : " +title);
		Assert.assertEquals(title, "Reports dashboard");
	}
	
	@Test(priority = 2)
	public void verifyHomePageHeaderTest()
	{
		String header =  homePage.getHomePageHeaderName();
		System.out.println("Home page header : " +header);
		Assert.assertEquals(header, "Sales Dashboard");
	}
	@Test(priority = 3)
	public void VerifyDashboardNameTest()
	{
		String dashboardName = homePage.getDashboardName();
		System.out.println("Dashboard name is : " +dashboardName );
		Assert.assertEquals(dashboardName, "Create dashboard");
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	

}
