package com.guru99.Assignment1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Assignment_Mobile{

	WebDriver driver;
	String baseURL="http://live.guru99.com/index.php/";
	@BeforeClass
	public void setUp()
	{
		System.setProperty("webdriver.gecko.driver","./Library/drivers/geckodriver.exe");
		driver=new FirefoxDriver();
		
		//implicit Wait
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		//navigate to URL
		driver.get(baseURL);
	}
	
	@Test(priority=1)
	public void verify_Title()
	{
		String titletext=driver.findElement(By.xpath("//div[@class='page-title']/h2")).getText();
		
		if(titletext.contains("THIS IS DEMO SITE  "))
		{
			Assert.assertTrue(true);
			System.out.println("Title Verified");
		}
		else
		Assert.assertFalse(false,"Title Not Matched");
	}
	
	@Test(priority=2)
	public void verify_MobileLink()
	{
		//click on mobile 
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		//get Text
		String text=driver.findElement(By.xpath("//h1[text()='Mobile']")).getText();
		
		Assert.assertEquals(text, "MOBILE");
	}
	
	@Test(priority=3)
	public void verify_SortByName() throws InterruptedException
	{
		//select the dropdown
		
		Select drpdwn=new Select(driver.findElement(By.xpath("//select[@title='Sort By']")));
		
		//select name as dropdown option
		drpdwn.selectByVisibleText("Name");
		
		
		//get the list of MObiles
		List<WebElement> listmobile=driver.findElements(By.xpath("//h2/a"));
		
		//check names are sorted
		boolean issorted=true;
		for(int i=0;i<listmobile.size()-1;i++)
		{
			if((listmobile.get(i).getText()).compareTo((listmobile.get(i+1).getText()))>0)
			{
				issorted=false;
				break;
			}	
		}
		if(issorted)
			Assert.assertTrue(true,"MobileNames are in Ascending Order\"");
		else
			Assert.assertTrue(false,"MobileNames are Not in Ascending Order\"");
		
	}
	
	
	@AfterClass
	public void TearDown()
	{
		driver.close();
	}
	
	
	
	
	
}
