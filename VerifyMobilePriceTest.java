/*
 * Project Name: Guru99 eCommerce Live Project
 * Author: Rajashekar KM
 * Date :22-05-2019
 */

package com.guru99.Assignment1;

import java.io.File;
import java.io.IOException;
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

public class VerifyMobilePriceTest {

	WebDriver driver;
	String baseURL="http://live.guru99.com/index.php/";
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.gecko.driver","./Library/drivers/geckodriver.exe");
		driver=new FirefoxDriver();
		
		//implicit Wait and PageLoad
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//navigate to URL
		driver.get(baseURL);
	}
	
	@Test
	public void verifyPriceOfSony() throws IOException
	{
		//click on mobile Menu
				driver.findElement(By.xpath("//a[text()='Mobile']")).click();
				
		//read the Price of SonyExperia Mobile
		String sonyPrice=driver.findElement(By.cssSelector("#product-price-1>span")).getText();
				
				
		//click on SonyExperia Mobile
		driver.findElement(By.xpath("//a[@title='Xperia']")).click();
		
		//Read the Price in Details Page
		String verifyPrice=driver.findElement(By.cssSelector(".price")).getText();
		
		//Compare the both the Prices 
		Assert.assertEquals(sonyPrice, verifyPrice,"Price Not Matched");
		
		
		//This will take screenShot of Sony Experia Details Page
		TakesScreenshot ts=(TakesScreenshot)driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		String path="C:\\Guru99 eCommerce Live Project\\Day01_TestCase1\\SonyMobilePrice.png";
		FileUtils.copyFile(srcFile, new File(path));
	}
	@AfterMethod
	public void TearDown()
	{
		driver.close();
	}
	
}
