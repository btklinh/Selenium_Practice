package com.java.basic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.AbstractPage;

public class Part_05_Multibrowser extends AbstractPage {

	String projectPath = System.getProperty("user.dir");
	WebDriver driver;

	@BeforeTest(groups = { "test", "bonding" })
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		System.out.println("Access hrm system");
		driver.get("https://hrm.anhtester.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterTest(groups = { "test", "bonding" })
	public void afterTest() {
		driver.quit();
	}

	@Test(groups = { "test", "bonding" })
	public void Test_01_Login() {
		System.out.println("Login - Step 01: Input 'Username'");
	}

	@Test(groups = { "bonding" })
	public void Test_02_Verify_Helpdesk_Menu() {
		System.out.println("Helpdesk Menu - Step 01: Click to 'Helpdesk' menu'");
	}

	@Test(groups = { "test" })
	public void Test_03_Click_Help_Desk_Menu() {
		System.out.println("Helpdesk Menu - Step 01: Click to 'Helpdesk' menu'");
	}

	@Test(groups = { "bonding" })
	public void Test_04_Verify_Logout() {
		System.out.println("Logout - Step 01: Click to Logout button");
	}
}
