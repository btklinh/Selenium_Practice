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

public class Practice_08_Update_Order extends AbstractTest {

	WebDriver driver;
	HomePageObject homePage;
	MyAccountPageObject myAccount;
	ShoppingCartPageObject myCartPage;
	String email, password, country, state, zip;
	CheckOutPageObject checkOutPage;
	int flatRate, grandTotal, grandTotalAfter, totalPrice, quantity, qty, subTotal;

	@Parameters("browser")
	@BeforeClass
	public void BeforeClass(String browser) {
		driver = getBrowserDriver(browser);
		homePage = new HomePageObject(driver);
		email = "linhbui1993@gmail.com";
		password = "11111111";
		country = "United States";
		state = "Alabama";
		zip = "100900";
		quantity = 2;
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
	public void TC_02_Verify_Grand_Total_Update_Qty_Order() {
		System.out.println("Step 02 - Click on REORDER link");
		myAccount.clickToReorderLink();
		myCartPage = new ShoppingCartPageObject(driver);

		System.out.println("Step 03 - Get grand total, subtotal, qty before update quatity");

		grandTotal = formatStringToInt(myCartPage.getGrandTotalPrice());
		System.out.println("---Grand Total before update quantity: " + grandTotal + "---");

		subTotal = formatStringToInt(myCartPage.getSubtotalPrice());
		System.out.println("---SubTotal value: " + subTotal + "---");

		qty = formatStringToInt(myCartPage.getQty());
		System.out.println("---Quantity value: " + qty + "---");

		System.out.println("Step 04 - Change Qty and click UPDATE");
		myCartPage.inputToQtyTextBox(quantity);
		myCartPage.clickToUpdateButton();

		grandTotalAfter = formatStringToInt(myCartPage.getGrandTotalPrice());
		System.out.println("---Grand Total after update quantity: " + grandTotalAfter + "---");

		System.out.println("Step 05 - Verify Grand Total is changed");
		Assert.assertEquals(grandTotal += subTotal * (quantity - qty), grandTotalAfter);
	}

	@Test
	public void TC_03_Verify_Grand_Total_Update_Shipping_Tax() {


//		System.out.println("Step 05 - Input ESTIMATE SHIPPING TAX infor and click Update total");
//		System.out.println("Select Country");
//		myCartPage.selectCountryDropbox(country);
//
//		System.out.println("Select State/Province");
//		myCartPage.selectStateDropbox(state);
//
//		System.out.println("Input to Zip text");
//		myCartPage.inputToZipText(zip);
//
//		System.out.println("Click to ESTIMATE link");
//		myCartPage.clickToEstimateLink();
//		flatRate = formatStringToInt(myCartPage.getFlatRate());
//
//		System.out.println("Click to UPDATE TOTAL button");
//		myCartPage.clickToUpdateTotalButton();
//
//		System.out.println("Step 06 - Verify Grand Total is updated");
//		Assert.assertEquals(grandTotalAfter += flatRate, myCartPage.getGrandTotalPrice());
	}

	@Test
	public void TC_04_Verify_Input_Valid_Billing_Shipping_Info() {
		System.out.println("Step 07 - Click to Proceed to Checkout button");

		myCartPage.clickToProceedToCheckOutButton();
		checkOutPage = new CheckOutPageObject(driver);

		System.out.println("Step 08 - Select Ship to this address and click Continuo");
		checkOutPage.clickToShipContinueButton();

		System.out.println("Step 09 - Click Continue on SHIPPING METHOD");
		flatRate = formatStringToInt(checkOutPage.getFlatRate());
		checkOutPage.clickToShippingMethodContinueButton();

		System.out.println("Step 10 - Select Check/Money order in PAYMENT INFORMATION -> Continue");
		checkOutPage.selectCheckMoneyOrder();
		checkOutPage.clickToPaymentSaveContinueButton();

		System.out.println("Step 11 - Check total money");
		totalPrice = formatStringToInt(checkOutPage.getTotalPrice());

		//System.out.println("---Grand Total on Checkout page " + totalPrice + "---");
		//Assert.assertEquals(grandTotalAfter, totalPrice);

		System.out.println("Step 12 - Click PLACE ORDER button");
		checkOutPage.clickToPlaceOrderButton();
		String s = checkOutPage.getSucceedMessage();
		Assert.assertEquals(s, "YOUR ORDER HAS BEEN RECEIVED.");

	}

	@AfterClass()
	public void AfterClass() {
		driver.quit();
	}

	public int formatStringToInt(String s) {
		int price = Integer.parseInt(s.trim().replace("$", "").replace(".00", "").replace(",", ""));
		return price;
	}

}
