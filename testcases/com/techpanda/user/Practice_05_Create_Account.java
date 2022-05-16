package com.techpanda.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.HomePageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.MyWishListPageObject;
import pageObjects.TVPageObject;

public class Practice_05_Create_Account extends AbstractTest {

	WebDriver driver;
	HomePageObject homePage;
	MyAccountPageObject myAccount;
	TVPageObject tvPage;
	MyWishListPageObject myWishListPage;
	String firstName, lastName, email, password, confirmPassword, shareMessage, shareEmail;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = getBrowserDriver(browser);

		homePage = new HomePageObject(driver);
		firstName = "Linh";
		lastName = "Bui";
		email = "linhbui" + randomNumber() + "@gmail.com";
		password = "11111111";
		confirmPassword = "11111111";
		shareEmail = "hoangthibinh"+randomNumber()+"@gmail.com";
		shareMessage = "test";
	}

	@Test
	public void TC_01_Create_Valid_Account() {
		System.out.println("Step 01 - Click on Account menu and click on My Account");
		
		homePage.clickToMyAccountLink();
		myAccount = new MyAccountPageObject(driver);

		System.out.println("Step 02 - Click on Create Account link");
		myAccount.clickToCreateAnAccount();

		System.out.println("Step 03 - Fill valid information and click Register");
		System.out.println("Input first name");
		myAccount.inputToFirstNameText(firstName);
		
		System.out.println("Input last name");
		myAccount.inputToLastNameText(lastName);
		
		System.out.println("Input email");
		myAccount.inputToEmailText(email);
		
		System.out.println("Input password");
		myAccount.inputToPasswordText(password);
		
		System.out.println("Input confirm password");
		myAccount.inputToConfirmPasswordText(confirmPassword);
		
		System.out.println("Click Register button");
		myAccount.clickToRegisterButton();
		
		System.out.println("Step 04 - Check Register successfully");
		Assert.assertEquals(myAccount.getSuceedRegisterMess(), "Thank you for registering with Main Website Store.");
	}

	@Test
	public void TC_02_Add_To_Wish_List() {
		System.out.println("Step 01 - Click to TV menu ");
		myAccount.clickToTVMenu();
		tvPage = new TVPageObject(driver);

		System.out.println("Step 02 - Click to Add product to your wish list");
		tvPage.clickToAddToWishList();
		myWishListPage = new MyWishListPageObject(driver);

		System.out.println("Step 03 - Click SHARE WISHLIST");
		myWishListPage.clickToShareWishListLink();

		
		System.out.println("Step 04 - Input valid email and message then click Share");
		myWishListPage.inputToEmailText(shareEmail);
		myWishListPage.inputToMessageText(shareMessage);
		myWishListPage.clickToShareWishListButton();

			
		System.out.println("Step 05 - Check Wishlist is shared");
		Assert.assertEquals(myWishListPage.getShareMessageText(), "Your Wishlist has been shared.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}

}
