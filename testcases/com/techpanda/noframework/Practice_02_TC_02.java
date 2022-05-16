package com.techpanda.noframework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Practice_02_TC_02 {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String pageUrl;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Access live techpanda website
		driver.get("https://live.techpanda.org/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);			  
	}

	
	@Test
	public void TC_01_Verify_Price_Sony() {
		//Click to Mobile link 
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);			
		String priceInList = driver.findElement(By.xpath("//span[@id='product-price-1']")).getText().trim();
		
		
		//Click to Sony Xperia title 
		driver.findElement(By.xpath("//a[text()='Sony Xperia']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);			
		
		String priceInDetailPage = driver.findElement(By.xpath("//span[@class='price']")).getText().trim();
		
		//Compare
		Assert.assertEquals(priceInList.compareToIgnoreCase(priceInDetailPage),0);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
