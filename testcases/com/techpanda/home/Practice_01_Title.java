package com.techpanda.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;

public class Practice_01_Title {
	WebDriver driver;
	String currentTitle, expectedTitle;
	String projectPath = System.getProperty("user.dir");
	HomePageObject homePage;

	@Test
	public void TC_01_Verify_Title() {
		System.out.println("Step 01 - Verify title");
		currentTitle = homePage.getHomePageTitle();
		expectedTitle = "THIS IS DEMO SITE FOR ";
		Assert.assertEquals(currentTitle, expectedTitle);
	}

	@BeforeTest
	public void beforeClass() {
		System.out.println("Precondition - Step 01: Open browser and Navigate to app url");
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver.exe");
		driver = new FirefoxDriver();

		homePage = new HomePageObject(driver);
		homePage.openUrl("https://live.techpanda.org/");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
