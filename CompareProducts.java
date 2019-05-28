/*
 * Project Name: Guru99 eCommerce Live Project
 * Author: Rajashekar KM
 * purpose : Compare Products
 */


package com.guru99.Assignment1;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CompareProducts {
	WebDriver driver;
	String baseURL="http://live.guru99.com/index.php/";
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.gecko.driver","./Library/drivers/geckodriver.exe");
		driver=new FirefoxDriver();
		
		//implicit Wait
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//navigate to URL
		driver.get(baseURL);
	}
	
	
	@Test
	public void compareProducts() throws IOException
	{
		
		//click on Mobile Menu
				driver.findElement(By.linkText("MOBILE")).click();
				
		//Add SonyXperia and IPHONE mobile For Compare
		driver.findElement(By.xpath("//li[@class='item last']/a[@title='Xperia']/following::a[4]")).click();
		driver.findElement(By.xpath("//li[@class='item last']/a[@title='IPhone']/following::a[4]")).click();
		
		String parentWindow=driver.getWindowHandle();
		//compare the products
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		
		Set<String> windowTabs=driver.getWindowHandles();
		
		for(String window:windowTabs)
		{
			if(!window.equals(parentWindow))
			{
				//switch to Popup
				driver.switchTo().window(window);
				
				//verify Pop up
				boolean verifyPOpup=driver.findElement(By.tagName("h1")).getText().equalsIgnoreCase("Compare Products");
				
				Assert.assertEquals(verifyPOpup, true,"Pop up Window Opened");
				
				//take screen Shot of Popup
				File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile,new File("C:\\Guru99 eCommerce Live Project\\Day01_TestCase1\\CompareProducts.png"));
				
				driver.close();
			}
		}
		
	}
	
	@AfterMethod
	public void TearDown()
	{
		driver.quit();
	}
}
