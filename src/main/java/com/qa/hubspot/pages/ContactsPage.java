package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.basepage.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class ContactsPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	By CreateContact = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]");	
//	By createContactForm  =By.xpath("(//button[@type='button']/span[text()='Create contact'][position()=2]");
	By createContactForm =By.xpath("(//span[contains(text(),'Create contact')])[position() =2]");
	By email = By.xpath("//input[@data-field='email']");
	By firstname = By.xpath("//input[@data-field='firstname']");
	By lastname=By.xpath("//input[@data-field='lastname']");
	By jobtitle =By.xpath("//input[@data-field='jobtitle']");
	
	public ContactsPage(WebDriver driver)
	{
		this.driver =driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public String getContactsPageTitle()
	{
		elementUtil.waitForTitlePresent(Constants.CONTACTS_PAGE_TITLE);
		return elementUtil.getPageTitle();
	}
	
	public void createNewContacts(String mail,String FN, String LN, String jobTitle)
	{
		elementUtil.waitForElementPresent(CreateContact);
		elementUtil.doClick(CreateContact);
		elementUtil.waitForElementPresent(email);
		
		elementUtil.sendKeys(email, mail);
		elementUtil.sendKeys(firstname, FN);
		elementUtil.sendKeys(lastname, LN);
		elementUtil.sendKeys(jobtitle, jobTitle);
		
		elementUtil.waitForElementPresent(createContactForm);
//		elementUtil.doClick(createContactForm);
		
      	jsUtil.clickElementByJS(elementUtil.getElement(createContactForm));
	}
	
	

}
