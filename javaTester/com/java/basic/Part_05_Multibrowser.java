package com.java.basic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.AbstractPage;

public class Part_05_Multibrowser extends AbstractPage{

	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		
		System.out.println("Access hrm system");
		driver.get("https://hrm.anhtester.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	@Test(description = "Login to the hrm system")
	public void Test_01_Login() {
		System.out.println("Input 'Username'");
		waitToElementVisible(driver, "//input[@id='iusername']");
		sendKeysToElement(driver, "//input[@id='iusername']", "leader01");
		
		System.out.println("Input 'Password'");
		waitToElementVisible(driver, "//input[@id='ipassword']");
		sendKeysToElement(driver, "//input[@id='ipassword']", "123456");
		
		System.out.println("Click to 'Login' button");
		waitToElementClickable(driver, "//button[@type='submit']");
		clickToElement(driver, "//button[@type='submit']");
		
		System.out.println("Verify Login successfully");
		waitToElementVisible(driver, "//a[@class='btn btn-smb btn-outline-primary rounded-pill']");		
		Assert.assertTrue(isControlDisplayed(driver, "//a[@class='btn btn-smb btn-outline-primary rounded-pill']"));
	}
	
	@Test
	public void Test_01_Verify_Diem_Danh_Menu() {

	}
}
