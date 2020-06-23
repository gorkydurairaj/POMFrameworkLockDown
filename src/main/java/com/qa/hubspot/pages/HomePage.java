package com.qa.hubspot.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.basepage.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By header =By.xpath("//span/h1[text() ='Sales Dashboard']");
	By createDashboard = By.xpath("//span[text()='Create dashboard']");
	By mainContacts = By.id("nav-primary-contacts-branch");
	By childContacts = By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle()
	{
//		return driver.getTitle();
		elementUtil.waitForTitlePresent(Constants.HOME_PAGE_TITLE);
		return elementUtil.getPageTitle();
	}
	
	public String getHomePageHeaderName()
	{
//		return driver.findElement(header).getText();
		elementUtil.waitForElementPresent(header);
		return elementUtil.doGetText(header);
	}
	
	public String getDashboardName()
	{
//		return driver.findElement(createDashboard).getText();
		elementUtil.waitForElementPresent(createDashboard);
		return elementUtil.doGetText(createDashboard);
	}
	
	public void clickOnContacts()
	{
		elementUtil.waitForElementPresent(mainContacts);
		elementUtil.doClick(mainContacts);
		elementUtil.waitForElementPresent(childContacts);
		elementUtil.doClick(childContacts);
	}
	
	public ContactsPage goToContactsPage()
	{
		clickOnContacts();
		return new ContactsPage(driver);
	}
	
	

}
