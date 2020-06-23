package com.qa.hubspot.basepage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;






import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasePage {
	
	WebDriver driver;
	Properties prop;
	public static  boolean highlightElement;
	
	public WebDriver init_Driver(String browserName)
	 {
		highlightElement = prop.getProperty("highlight").equals("yes")? true:false ;
		System.out.println("Browser Name is  : " +browserName);
		if(browserName.equalsIgnoreCase("chrome"))
		{
//			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver","E:\\chrome83\\chromedriver.exe");
			
			if(prop.getProperty("headless").equals("yes"))
			{
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			}
			else {
				driver = new ChromeDriver();
			}
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else 
			{
				System.out.println(browserName + "is not defined properly,please correct the browser name ");
			}
		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		return driver;
		}
	
	public Properties init_Properties()
	{
		prop = new Properties();
		
//		String path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
		
		String path = null;
		String env = null;
		
		try{
			env = System.getProperty("env");
			
			if(env.equals("qa"))
			{
				path = ".\\src\\main\\java \\com\\qa\\hubspot\\config\\qa.config.properties";
			}
			else if(env.equals("stg"))
			{
				path =".\\src\\main\\java\\com\\qa\\hubspot\\config\\stg.config.properties";
			}
		}
		catch(Exception e)
		{
		     path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
		}
		
		
		try {
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip); 
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("some issue with config file.Please check the config.properties ");
		} 
		catch (IOException e) {

			e.printStackTrace();
		}
		
		return prop;
	}
	
	

}
	
	


