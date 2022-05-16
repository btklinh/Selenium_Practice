package com.techpanda.mobile;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.HomePageObject;
import pageObjects.MobilePageObject;

public class Practice_01_Filter extends AbstractTest {

	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	String pageUrl, currentTitle, expectedTitle;
	HomePageObject homePage;
	MobilePageObject mobilePage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver = getBrowserDriver(browserName);

		homePage = new HomePageObject(driver);
		
	}


	@Test(priority = 1)
	public void TC_02_Product_Sort_By_Price() {

		System.out.println("Step 01 - Sort by Price: Click to Mobile link");
	//	mobilePage = homePage.clickToMobileLink();
		mobilePage = (MobilePageObject) homePage.openMenuPageByName(driver, "Mobile");
	//	mobilePage = new MobilePageObject(driver);

		System.out.println("Step 01 - Sort by Price: Get all product name before sort and sort by price");
		List<String> priceBeforeSort = mobilePage.getAllMobilePrice();
		mobilePage.sortByType(priceBeforeSort);

		System.out.println("Step 02 - Sort by Price: Click to Sort by Price");
		mobilePage.selectSortByType("Price");

		System.out.println("Step 03 - Sort by Price: Get all product price after sort");
		List<String> priceAfterSort = mobilePage.getAllMobilePrice();

		System.out.println("Step 04 - Sort by Price: Verify that product names are sort");
		Assert.assertEquals(priceBeforeSort, priceAfterSort);
	}

	@Test(priority = 2)
	public void TC_01_Product_Sort_By_Name() {

		System.out.println("Step 02 - Sort by Name: Get all product name before sort and sort by name");

		List<String> beforeSortByName = mobilePage.getAllProductName();
		mobilePage.sortByType(beforeSortByName);

		System.out.println("Step 03 - Sort by Name: Click to Sort by Name");
		mobilePage.selectSortByType("Name");

		System.out.println("Step 04 - Sort by Name: Get all product name after sort");
		List<String> afterSortByName = mobilePage.getAllProductName();

		System.out.println("Step 04 - Sort by Name: Verify that product names are sort");
		Assert.assertEquals(beforeSortByName, afterSortByName);

	}

	@Test(priority = 3)
	public void TC_03_Produce_Sort_By_Review() {
		System.out.println("Step 01 - Sort by Review: Get all produce rating before sort and sort by rating");
		List<Integer> productBeforeSortByRate = mobilePage.getAllMobileRate();
		mobilePage.sortByReview(productBeforeSortByRate);
		for (int p : productBeforeSortByRate)
			System.out.println(p);

		System.out.println("Step 02 - Sort by Review: Click to Sort by Position");
		mobilePage.selectSortByType("Position");

		System.out.println("Step 03 - Sort by Review: Get all product rating after sort");
		List<Integer> productAfterSortByRate = mobilePage.getAllMobileRate();

		System.out.println("Step 04 - Sort by Review: Verify that product rating are sort");
		Assert.assertEquals(productBeforeSortByRate, productAfterSortByRate);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
