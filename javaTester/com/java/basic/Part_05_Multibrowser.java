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
		driver.manage().window().maximize();
		
		System.out.println("Access hrm system");
		driver.get("https://hrm.anhtester.com/");	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	@Test(priority=1)
	public void Test_01_Login() {
		System.out.println("Login - Step 01: Input 'Username'");
		waitToElementVisible(driver, "//input[@id='iusername']");
		sendKeysToElement(driver, "//input[@id='iusername']", "admin01");
		
		System.out.println("Login - Step 02: Input 'Password'");
		waitToElementVisible(driver, "//input[@id='ipassword']");
		sendKeysToElement(driver, "//input[@id='ipassword']", "123456");
		
		System.out.println("Login - Step 03: Click to 'Login' button");
		waitToElementClickable(driver, "//button[@type='submit']");
		clickToElement(driver, "//button[@type='submit']");
		
		System.out.println("Login - Step 04: Verify Login successfully");
		waitToElementVisible(driver, "//i[@class='feather icon-power']/parent::a");		
		Assert.assertTrue(isControlDisplayed(driver, "//i[@class='feather icon-power']/parent::a"));
	}
	
	
	@Test(priority=3)
	public void Test_02_Verify_Helpdesk_Menu()  {
		System.out.println("Helpdesk Menu - Step 01: Click to 'Helpdesk' menu'");
		waitToElementClickable(driver, "//span[contains(text(),'Helpdesk')]");
		clickToElement(driver, "//span[contains(text(),'Helpdesk')]");
				
		System.out.println("Helpdesk Menu - Step 02: Verify Helpdesk page is displayed");
		waitToElementVisible(driver, "//ul[@class='breadcrumb']/li[2]");		
		Assert.assertEquals(getElementText(driver, "//ul[@class='breadcrumb']/li[2]"),"Helpdesk" );
	}
	
	@Test(priority=2)
	public void Test_03_test()  {
		System.out.println("Helpdesk Menu - Step 01: Click to 'Helpdesk' menu'");
		waitToElementClickable(driver, "//span[contains(text(),'Helpdesk')]");
		clickToElement(driver, "//span[contains(text(),'Helpdesk')]");
				
		System.out.println("Helpdesk Menu - Step 02: Verify Helpdesk page is displayed");
		waitToElementVisible(driver, "//ul[@class='breadcrumb']/li[2]");		
		Assert.assertEquals(getElementText(driver, "//ul[@class='breadcrumb']/li[2]"),"Helpdesk" );
	}
	
		@Test(priority=4)
	public void Test_04_Verify_Logout() {
		System.out.println("Logout - Step 01: Click to Logout button");
		waitToElementVisible(driver, "//i[@class='feather icon-power']/parent::a");		
		clickToElement(driver, "//i[@class='feather icon-power']/parent::a");
		
		System.out.println("Logout - Step 02: Verify Login form is displayed");
		waitToElementVisible(driver, "//form[@class='form-timehrm']");		
		Assert.assertTrue(isControlDisplayed(driver, "//form[@class='form-timehrm']"));
	}
}
