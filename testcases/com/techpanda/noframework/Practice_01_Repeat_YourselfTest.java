package com.techpanda.noframework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

public class Practice_01_Repeat_YourselfTest {
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
	public void TC_01_Verify_Title() {
		//get page url
		pageUrl = driver.getCurrentUrl();
		
		//If title contain THIS IS DEMO SITE FOR text, pass
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//h2")).getText().contains("THIS IS DEMO SITE"));		
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		String s = driver.findElement(By.xpath("//body/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[2]/div[1]/div[2]/div[1]/div[1]")).getAttribute("style");
		
		System.out.println("Attribute of stype"+s);
		
	}
	

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
