package com.techpanda.mobile;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.HomePageObject;
import pageObjects.MobilePageObject;
import pageObjects.ProductDetailPageObject;

public class Practice_02_Mobile_Information extends AbstractTest {
	WebDriver driver;
	HomePageObject homePage;
	MobilePageObject mobilePage;
	ProductDetailPageObject productDetailPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = getBrowserDriver(browser);
		homePage = new HomePageObject(driver);

	}

	@Test
	public void TC_01_Verify_Price_Sony() {
		System.out.println("Step 01 - Click to Mobile link");
		mobilePage = (MobilePageObject) homePage.openMenuPageByName(driver, "Mobile");

		System.out.println("Step 02 - Read the price of Sony mobile");
		mobilePage = new MobilePageObject(driver);
		String priceInList = mobilePage.getPriceOfProduct();

		System.out.println("Step 03 - Click to Sony Xperia title and read the price of Sony mobile");
		mobilePage.clickToSonyTitle();
		productDetailPage = new ProductDetailPageObject(driver);
		String priceInDetailPage = productDetailPage.getPriceOfProduct();

		System.out.println("Step 04 - Compare price of Sony in both page");
		Assert.assertEquals(priceInList, priceInDetailPage);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
