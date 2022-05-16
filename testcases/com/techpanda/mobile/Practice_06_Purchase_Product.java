package com.techpanda.mobile;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.CheckOutPageObject;
import pageObjects.HomePageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.ShoppingCartPageObject;
import pageObjects.MyWishListPageObject;

public class Practice_06_Purchase_Product extends AbstractTest {

	WebDriver driver;
	HomePageObject homePage;
	MyAccountPageObject myAccount;
	MyWishListPageObject myWishListPage;
	ShoppingCartPageObject myCartPage;
	CheckOutPageObject checkOutPage;
	String email, password;
	int productPrice,totalPrice,flatRate;
	String address, city, country, state, zip, telephone;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = getBrowserDriver(browser);

		homePage = new HomePageObject(driver);

		email = "linhbui1993@gmail.com";
		password = "11111111";
		address = "ABC";
		city = "New York";
		country = "United States";
		state = "New York";
		zip = "542896";
		telephone = "12345678";
	}

	@Test
	public void TC_01_Login() {
		System.out.println("Step 01 - Login");
		homePage.clickToMyAccountLink();
		myAccount = new MyAccountPageObject(driver);
		myAccount.inputLoginEmailText(email);
		myAccount.inputLoginPasswordText(password);
		myAccount.clickToLoginButton();
	}

	@Test
	public void TC_02_Purchase() {

		System.out.println("Step 02 - Click to My wishlist link");
		myAccount.clickToMyWishListLink();
		myWishListPage = new MyWishListPageObject(driver);

		System.out.println("Step 03 - Click to Add to card button");
		myWishListPage.clickToAddToCartButton();
		myCartPage = new ShoppingCartPageObject(driver);

		System.out.println("Step 04 - Click to Proceed to checkout");
		productPrice = formatString(myCartPage.getGrandTotalPrice());
		myCartPage.clickToProceedToCheckOutButton();
		checkOutPage = new CheckOutPageObject(driver);

		System.out.println("Step 05 - Input valid Billing information - select Ship to this address -> Continue");
		checkOutPage.selectNewAddressDropbox();

		System.out.println("Input to address text");
		checkOutPage.inputToAddressText(address);

		System.out.println("Select Country");
		checkOutPage.selectCountryDropbox(country);

		System.out.println("Select State/Province");
		checkOutPage.selectStateDropbox(state);

		System.out.println("Input to city text");
		checkOutPage.inputToCityText(city);

		System.out.println("Input to Zip text");
		checkOutPage.inputToZipText(zip);

		System.out.println("Input to Telephone");
		checkOutPage.inputToTelephoneText(telephone);

		System.out.println("Select Ship to this address and click Continuo");
		checkOutPage.selectShipToThisAddress();
		checkOutPage.clickToShipContinueButton();

		System.out.println("Step 06 - Click Continue on SHIPPING METHOD");
		flatRate = formatString(checkOutPage.getFlatRate());
		checkOutPage.clickToShippingMethodContinueButton();

		System.out.println("Step 07 - Select Check/Money order in PAYMENT INFORMATION -> Continue");
		checkOutPage.selectCheckMoneyOrder();
		checkOutPage.clickToPaymentSaveContinueButton();

		System.out.println("Check total money");
		totalPrice = formatString(checkOutPage.getTotalPrice());

		System.out.println("Step 08 - Click PLACE ORDER button");
		checkOutPage.clickToPlaceOrderButton();
		String s = checkOutPage.getSucceedMessage();
		Assert.assertEquals(s, "YOUR ORDER HAS BEEN RECEIVED.");
		
//		finalPage = new FinalPageObject(driver);
//		String s = finalPage.getSuceedMessage();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		Assert.assertEquals(s, "YOUR ORDER HAS BEEN RECEIVED.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int formatString(String s) {
		int price = Integer.parseInt(s.trim().replace("$", "").replace(".00", "").replace(",", ""));
		return price;
	}

}
