package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.basepage.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest {
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	ContactsPage contactsPage;
	
	@BeforeMethod
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
		contactsPage = homePage.goToContactsPage();
	}
	
	@Test(priority =1)
	public void ContactsPageTitleTest()
	{
		String title  = contactsPage.getContactsPageTitle();
		System.out.println("Contacts Page Title is :" +title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getContactsTestData()
	{
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	@Test(priority =2, dataProvider = "getContactsTestData")
	public void createContactTest(String email,String firstname, String lastname, String jobTitle)
	{
		contactsPage.createNewContacts(email,firstname,lastname,jobTitle);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
