package com.techpanda.page;

import java.util.ArrayList;
//import java.lang.reflect.Array;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.MobilePageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.ShoppingCartPageObject;

public class TC_01_Header extends AbstractTest {

	WebDriver driver;
	HomePageObject homePage;
	MyAccountPageObject myAccountPage;
	ShoppingCartPageObject shoppingCartPage;
	MobilePageObject mobilePage;
	List<String> menuList, expectedMenuList;

	@Test
	public void TC_01_Verify_Header_Submenu_Account_Without_Login() {
		log.info("TC01 - Step 01: Click to 'Account' link in the header");
		homePage.clickToHeaderAccountLink(driver);

		log.info("TC01 - Step 02: Get all submenu displayed");
		menuList = homePage.getMenuListWhenClickAccountLink(driver);
		

		expectedMenuList = new ArrayList<String>();
		expectedMenuList.add("My Account");
		expectedMenuList.add("My Wishlist");
		expectedMenuList.add("My Cart");
		expectedMenuList.add("Checkout");
		expectedMenuList.add("Register");
		expectedMenuList.add("Log In");

		log.info("TC01 - Step 03: Compare submenu displayed with expected menu");
		Assert.assertEquals(menuList, expectedMenuList);
	}

	@Test
	public void TC_03_Verify_Header_My_Account_Page() {
		log.info("TC03 - Step 01: Click to 'My Account' menu in the header");
		homePage.clickToHeaderMenuLinkByMenuName(driver, "My Account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
		
		log.info("TC03 - Step 02: Verify Login page is displayed");
		verifyEquals(myAccountPage.getPageHeader(driver), "LOGIN OR CREATE AN ACCOUNT");
	}

	@Test
	public void TC_05_Verify_Header_My_Wishlist_Page() {
		log.info("TC05 - Step 01: Click to 'Account' link in the header");
		myAccountPage.clickToHeaderAccountLink(driver);
		
		log.info("TC05 - Step 02: Click to 'My Wishlist' menu in the header");
		myAccountPage.clickToHeaderMenuLinkByMenuName(driver, "My Wishlist");
		System.out.println(myAccountPage.getPageHeader(driver));
		
		log.info("TC05 - Step 03: Verify Login page is displayed");
		verifyEquals(myAccountPage.getPageHeader(driver), "LOGIN OR CREATE AN ACCOUNT");
	}
	
	@Test
	public void TC_07_Verify_Header_My_Shopping_Cart_Blank() {
		log.info("TC07 - Step 01: Click to 'Account' link in the header");
		myAccountPage.clickToHeaderAccountLink(driver);
		
		log.info("TC07 - Step 02: Click to 'My Cart' menu in the header");
		myAccountPage.clickToHeaderMenuLinkByMenuName(driver, "My Cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		
		log.info("TC07 - Step 03: Verify Shopping Cart is displayed");
		verifyEquals(shoppingCartPage.getPageHeader(driver), "SHOPPING CART IS EMPTY");
	}

	@Test
	public void TC_08_Verify_Header_My_Shopping_Cart() {
		log.info("TC08 - Step 01: Click to 'Mobile' menu in the header");
		shoppingCartPage.clickToContentMenuByMenuName(driver, "Mobile");
		mobilePage = PageGeneratorManager.getMobilePage(driver);
		
		log.info("TC08 - Step 02: Add 'Iphone' to the cart");
		mobilePage.clickToAddToCartButton(driver, "IPhone");
		shoppingCartPage.clickToHeaderAccountLink(driver);
		
		log.info("TC08 - Step 03: Click to 'My Cart' menu in the header");
		shoppingCartPage.clickToHeaderMenuLinkByMenuName(driver, "My Cart");

		log.info("TC08 - Step 04: Verify Shopping Cart is displayed");
		verifyEquals(shoppingCartPage.getPageTitle(driver), "SHOPPING CART");
	}
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = getBrowserDriver(browser);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@AfterClass
	public void afterClass() {
		removeBrowserDriver();
	}
}
