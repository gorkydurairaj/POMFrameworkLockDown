package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, Constants.DEFAULT_TIMEOUT);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public boolean waitForTitlePresent(String title)
	{
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}
	
	public boolean waitForElementPresent(By locator)
	{
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		
		return true;
	}
	
	public boolean waitForElementVisible(By locator)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		return true;
	}
	
	public WebElement getElement(By locator)
	{
		WebElement element = null;		
		try {
			element = driver.findElement(locator);
			jsUtil.flash(element);
			}
		catch (Exception e) {
			System.out.println("some exception occured while getting the webelement");
		}
		
		return element;
	}
	
	public void doClick(By locator)
	{
		try {
			getElement(locator).click();
		} 
		catch (Exception e) {
			System.out.println("some exception occured while clicking the webelement");
		}
		
	}
	
	public void sendKeys(By locator,String value)
	{
		try {
			getElement(locator).sendKeys(value);
		} catch (Exception e) {
			System.out.println("some exception occured while passing the value in the field");
		}
		
	}
	
	public String getPageTitle()
	{ 
		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("some exception occured while getting the page title");
		}
		return null;
	}
	
	public boolean DoIsDisplayed(By locator)
	{
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("some exception occured while identity the web element");
		}
		return false;
		
	}
	
	public String doGetText(By locator)
	{
		try {
			return getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("some exception occured while getting the text  of the web element");
		}
		return null;
	
	}
	

}
