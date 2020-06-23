package com.qa.hubspot.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.basepage.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class LoginPage extends BasePage
{
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	By emailId = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public String  getLoginPageTitle()
	{
//		 return  driver.getTitle();
		elementUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE);
		return elementUtil.getPageTitle();
	}
	
	public String getLoginPageTitleByUsingJS()
	{
		return jsUtil.getTitleByJS();
	}
	public boolean getSignUpLink()
	{
//		return driver.findElement(signUpLink).isDisplayed();
		elementUtil.waitForElementPresent(signUpLink);
		return elementUtil.DoIsDisplayed(signUpLink);
	}
	
	public HomePage doLogin(Credentials userCred)
	{
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();
		elementUtil.waitForElementPresent(emailId);
		elementUtil.sendKeys(emailId, userCred.getAppUsername());
		elementUtil.sendKeys(password, userCred.getAppPassword());
		elementUtil.doClick(loginButton);
		
		return new HomePage(driver);
	}

}
