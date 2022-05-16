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
		homePage.clickToHeaderAccountLink(driver);

		menuList = homePage.getMenuListWhenClickAccountLink(driver);
		System.out.println(menuList);

		expectedMenuList = new ArrayList<String>();
		expectedMenuList.add("My Account");
		expectedMenuList.add("My Wishlist");
		expectedMenuList.add("My Cart");
		expectedMenuList.add("Checkout");
		expectedMenuList.add("Register");
		expectedMenuList.add("Log In");

		System.out.println(expectedMenuList);

		Assert.assertEquals(menuList, expectedMenuList);
	}

	@Test
	public void TC_03_Verify_Header_My_Account_Page() {
		homePage.clickToHeaderMenuLinkByMenuName(driver, "My Account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
		System.out.println(myAccountPage.getPageHeader(driver));
		Assert.assertEquals(myAccountPage.getPageHeader(driver), "LOGIN OR CREATE AN ACCOUNT");
	}

	@Test
	public void TC_05_Verify_Header_My_Wishlist_Page() {
		myAccountPage.clickToHeaderAccountLink(driver);
		myAccountPage.clickToHeaderMenuLinkByMenuName(driver, "My Wishlist");
		System.out.println(myAccountPage.getPageHeader(driver));
		Assert.assertEquals(myAccountPage.getPageHeader(driver), "LOGIN OR CREATE AN ACCOUNT");
	}
	
	@Test
	public void TC_07_Verify_Header_My_Shopping_Cart_Blank() {
		myAccountPage.clickToHeaderAccountLink(driver);
		myAccountPage.clickToHeaderMenuLinkByMenuName(driver, "My Cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		System.out.println("TC 08: " + shoppingCartPage.getPageHeader(driver));
		Assert.assertEquals(shoppingCartPage.getPageHeader(driver), "SHOPPING CART IS EMPTY");
	}

	@Test
	public void TC_08_Verify_Header_My_Shopping_Cart() {
		shoppingCartPage.clickToContentMenuByMenuName(driver, "Mobile");
		mobilePage = PageGeneratorManager.getMobilePage(driver);
		mobilePage.clickToAddToCartButton(driver, "IPhone");
		shoppingCartPage.clickToHeaderAccountLink(driver);
		shoppingCartPage.clickToHeaderMenuLinkByMenuName(driver, "My Cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		Assert.assertEquals(shoppingCartPage.getPageTitle(driver), "SHOPPING CART");
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
