/*
 * Project Name: Guru99 eCommerce Live Project
 * Author: Rajashekar KM
 */

package com.guru99.Assignment1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddCartTest {

	
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
	public void verifyAddCart()
	{
		//click on Mobile Menu
		driver.findElement(By.linkText("MOBILE")).click();
		
		//Add to Cart
		driver.findElement(By.xpath("//button[contains(@onclick,'product/1')]")).click();
		
		//change QTY value to 1000
		driver.findElement(By.cssSelector("input[title='Qty']")).sendKeys("1000");
		
		//click on Update Button
		driver.findElement(By.cssSelector("button[title='Update']")).clear();
		driver.findElement(By.cssSelector("button[title='Update']")).click();
		
		//Verify Error Message
		String msgerror=driver.findElement(By.cssSelector(".error-msg >ul>li>span")).getText();
		
		if(msgerror.contains("cannot be ordered"))
			Assert.assertTrue(true, "Error Message Verified");
		else
			Assert.assertTrue(false, "Invalid Error Message");
		
		//click on EmptyCart
		driver.findElement(By.xpath("//span[text()='Empty Cart']")).click();
		
		//verify Cart Empty
		String emptycartmsg=driver.findElement(By.cssSelector("div.page-title>h1")).getText();
		
		Assert.assertEquals(emptycartmsg, "SHOPPING CART IS EMPTY");
		
	}
	
	@AfterMethod
	public void TearDown()
	{
		driver.close();
	}
}
