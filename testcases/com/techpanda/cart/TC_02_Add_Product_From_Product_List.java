package com.techpanda.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.MobilePageObject;
import pageObjects.ProductDetailPageObject;
import pageObjects.ShoppingCartPageObject;
import pageObjects.TVPageObject;

public class TC_02_Add_Product_From_Product_List extends AbstractTest {

	WebDriver driver;
	HomePageObject homePage;
	MobilePageObject mobilePage;
	ShoppingCartPageObject shoppingCartPage;
	TVPageObject tvPage;
	int productPriceInList, productPriceInCart, productQtyInCart, productCartTotal;
	int product2PriceInList, product2PriceInCart, product2QtyInCart, product2CartTotal;
	int subtotal, tax;
	double grandtotal, grandtotalCal;
	String productName1, productName2, productPrice;
	ProductDetailPageObject productDetailPage;
	List<String> listProductsName;
	int position;

	@Parameters("browser")
	@BeforeMethod
	public void beforeClass(String browser) {
		System.out.println("Step 01 - Open LiveGuru99 site");
		driver = getBrowserDriver(browser);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	public void openPageAndGetListProducts(String pageName) {
		switch (pageName) {
		case "Mobile":
			mobilePage = (MobilePageObject) homePage.openMenuPageByName(driver, pageName);
			listProductsName = mobilePage.getProductsNameInListByPosition(driver, "title");
			break;
		case "TV":
			tvPage = (TVPageObject) homePage.openMenuPageByName(driver, pageName);
			listProductsName = tvPage.getProductsNameInListByPosition(driver, "title");
			break;
		}
		position = randomNumber(listProductsName.size());
	}

	public void TC_01_Verify_Adding_One_Product() {
		System.out.println("Step 02 - Click on Mobile link");
		openPageAndGetListProducts("Mobile");

		productName1 = listProductsName.get(position);
		productPrice = mobilePage.getProductPriceByPosition(driver, String.valueOf(position + 1));

		System.out.println("---Product Name: " + productName1 + "---");

		System.out.println("Step 03 - Add a product to cart");
		shoppingCartPage = mobilePage.clickToAddToCartButtonInListByPosition(driver, String.valueOf(position + 1));

		Assert.assertEquals(productName1 + " was added to your shopping cart.", shoppingCartPage.getAddProductText());

	}

	public void TC_02_Compare_Product_Price_In_List_And_ShoppingCart() {
		//TC_01_Verify_Adding_One_Product();
		System.out.println("On SHOPPING CART page, compare price with product price in product list page");
		Assert.assertEquals(productPrice, shoppingCartPage.getPriceOnCartByProductName(productName1));
	}

	public void TC_03_Verify_Product_Quantity() {
		//TC_01_Verify_Adding_One_Product();
		productQtyInCart = Integer.parseInt(shoppingCartPage.getQtyOnCartByProductName(productName1));
		Assert.assertEquals(1, productQtyInCart);
	}

	public void TC_04_Verify_Adding_Multi_Products() {
		// Click on Mobile Link
		openPageAndGetListProducts("Mobile");

		// Add all Products on Mobile page into shopping cart
		for (int i = 1; i <= listProductsName.size(); i++) {
			shoppingCartPage = mobilePage.clickToAddToCartButtonInListByPosition(driver, String.valueOf(i));
			if (i < listProductsName.size())
				openPageAndGetListProducts("Mobile");
		}

		// Verify Products are adding into shopping cart
		List<String> listProductsNameOnCartPage = new ArrayList<String>();
		listProductsNameOnCartPage = shoppingCartPage.getProductNameList();

		for (int i = 0; i < listProductsName.size() && i < listProductsNameOnCartPage.size(); i++) {
			System.out.println(listProductsName.get(i).compareToIgnoreCase(listProductsNameOnCartPage.get(i)));
			Assert.assertEquals(listProductsName.get(i).compareToIgnoreCase(listProductsNameOnCartPage.get(i)), 0);
		}
	}

	public void TC_05_Verify_Product_Total_Price() {
		// Click on Mobile Link
		openPageAndGetListProducts("TV");

		// Add all Products on TV page into shopping cart
		for (int i = 1; i <= listProductsName.size(); i++) {
			shoppingCartPage = tvPage.clickToAddToCartButtonInListByPosition(driver, String.valueOf(i));
			if (i < listProductsName.size())
				openPageAndGetListProducts("TV");
		}

		// Get Quantity list of all products in Cart
		List<Integer> listProductsQuantity = new ArrayList<Integer>();
		listProductsQuantity = shoppingCartPage.getProductQuantityList();
		System.out.println("Quantiy: " + listProductsQuantity);

		// Get Price list of all products in Cart
		List<Integer> listProductsPriceFormat = new ArrayList<Integer>();
		List<String> listProductsPrice = new ArrayList<String>();
		listProductsPrice = shoppingCartPage.getProductPriceList();
		for (String productPrice : listProductsPrice) {
			listProductsPriceFormat.add(formatString(productPrice));
		}
		System.out.println("Product Price: " + listProductsPriceFormat);

		// Calculate the Total price of each products and add it to the list
		List<Integer> listProductsTotalPrice = new ArrayList<Integer>();
		for (int i = 0; i < listProductsQuantity.size(); i++) {
			listProductsTotalPrice.add(listProductsQuantity.get(i) * listProductsPriceFormat.get(i));
		}
		System.out.println("Total price Caculate: " + listProductsTotalPrice);

		// Get Total Price list of all product displayed in Web UI
		List<Integer> listProductsTotalPriceWebFormat = new ArrayList<Integer>();
		List<String> listProductsTotalPriceWeb = new ArrayList<String>();
		listProductsTotalPriceWeb = shoppingCartPage.getTotalPrice();
		for (String productPriceWeb : listProductsTotalPriceWeb) {
			listProductsTotalPriceWebFormat.add(formatString(productPriceWeb));
		}
		System.out.println("Total price Web UI: " + listProductsTotalPriceWebFormat);

		// Compare values
		Assert.assertEquals(listProductsTotalPrice, listProductsTotalPriceWebFormat);
		
		
	}

	@Test
	public void TC_06_Verify_Cart_Grandtotal_Price() {
		// Click on Mobile Link
		openPageAndGetListProducts("TV");

		// Add all Products on TV page into shopping cart
		for (int i = 1; i <= listProductsName.size(); i++) {
			shoppingCartPage = tvPage.clickToAddToCartButtonInListByPosition(driver, String.valueOf(i));
			if (i < listProductsName.size())
				openPageAndGetListProducts("TV");
		}

		// Get Quantity list of all products in Cart
		List<Integer> listProductsQuantity = new ArrayList<Integer>();
		listProductsQuantity = shoppingCartPage.getProductQuantityList();
		System.out.println("Quantiy: " + listProductsQuantity);

		// Get Price list of all products in Cart
		List<Integer> listProductsPriceFormat = new ArrayList<Integer>();
		List<String> listProductsPrice = new ArrayList<String>();
		listProductsPrice = shoppingCartPage.getProductPriceList();
		for (String productPrice : listProductsPrice) {
			listProductsPriceFormat.add(formatString(productPrice));
		}
		System.out.println("Product Price: " + listProductsPriceFormat);

		// Calculate the Total price of each products and add it to the list
		List<Integer> listProductsTotalPrice = new ArrayList<Integer>();
		for (int i = 0; i < listProductsQuantity.size(); i++) {
			listProductsTotalPrice.add(listProductsQuantity.get(i) * listProductsPriceFormat.get(i));
		}
		System.out.println("Total price Caculate: " + listProductsTotalPrice);

		// Get Total Price list of all product displayed in Web UI
		List<Integer> listProductsTotalPriceWebFormat = new ArrayList<Integer>();
		List<String> listProductsTotalPriceWeb = new ArrayList<String>();
		listProductsTotalPriceWeb = shoppingCartPage.getTotalPrice();
		for (String productPriceWeb : listProductsTotalPriceWeb) {
			listProductsTotalPriceWebFormat.add(formatString(productPriceWeb));
		}
		System.out.println("Total price Web UI: " + listProductsTotalPriceWebFormat);

		// Compare values
		Assert.assertEquals(listProductsTotalPrice, listProductsTotalPriceWebFormat);

		// Get Subtotal price displayed in Web UI
		int subtotalPriceWeb = formatString(shoppingCartPage.getSubtotalPrice());
		System.out.println("Subtotal price Web UI: " + subtotalPriceWeb);

		// Sum of all products total price
		int sumOfTotalPrice = 0;
		for (int i : listProductsTotalPriceWebFormat) {
			sumOfTotalPrice += i;
		}
		System.out.println("Subtotal price caculate: " + sumOfTotalPrice);

		// Compare values
		Assert.assertEquals(sumOfTotalPrice, subtotalPriceWeb);

		// Apply Discount code
		shoppingCartPage.inputToDiscountCodesTextbox("GURU50");
		shoppingCartPage.clickToApplyLink();

		// Apply flat rate estimate
		shoppingCartPage.selectCountryDropbox("United States");
		shoppingCartPage.selectStateDropbox("Alabama");
		shoppingCartPage.inputToZipText("100106");
		shoppingCartPage.clickToEstimateLink();
		shoppingCartPage.selectTax();
		shoppingCartPage.clickToUpdateTotalButton();
		tax = formatString(shoppingCartPage.getFlatRate());
		System.out.println("tax: " + tax);

		// Get Grandtotal value displayed in Web UI
		grandtotal = formatString(shoppingCartPage.getGrandTotalPrice());
		System.out.println("grandtotal web ui: " + grandtotal);

		// Verify grandtotal calculate
		grandtotalCal = sumOfTotalPrice * (1 - 0.05) + tax;
		System.out.println("grandtotal cal: " + grandtotalCal);

		// Compare values
		Assert.assertEquals(grandtotalCal, grandtotal);
	}

	public void TC_07_Verify_Cart_Subtotal_Price() {
		// Click on Mobile Link
		openPageAndGetListProducts("TV");

		// Add all Products on TV page into shopping cart
		for (int i = 1; i <= listProductsName.size(); i++) {
			shoppingCartPage = tvPage.clickToAddToCartButtonInListByPosition(driver, String.valueOf(i));
			if (i < listProductsName.size())
				openPageAndGetListProducts("TV");
		}

		// Get Quantity list of all products in Cart
		List<Integer> listProductsQuantity = new ArrayList<Integer>();
		listProductsQuantity = shoppingCartPage.getProductQuantityList();
		System.out.println("Quantiy: " + listProductsQuantity);

		// Get Price list of all products in Cart
		List<Integer> listProductsPriceFormat = new ArrayList<Integer>();
		List<String> listProductsPrice = new ArrayList<String>();
		listProductsPrice = shoppingCartPage.getProductPriceList();
		for (String productPrice : listProductsPrice) {
			listProductsPriceFormat.add(formatString(productPrice));
		}
		System.out.println("Product Price: " + listProductsPriceFormat);

		// Calculate the Total price of each products and add it to the list
		List<Integer> listProductsTotalPrice = new ArrayList<Integer>();
		for (int i = 0; i < listProductsQuantity.size(); i++) {
			listProductsTotalPrice.add(listProductsQuantity.get(i) * listProductsPriceFormat.get(i));
		}
		System.out.println("Total price Caculate: " + listProductsTotalPrice);

		// Get Total Price list of all product displayed in Web UI
		List<Integer> listProductsTotalPriceWebFormat = new ArrayList<Integer>();
		List<String> listProductsTotalPriceWeb = new ArrayList<String>();
		listProductsTotalPriceWeb = shoppingCartPage.getTotalPrice();
		for (String productPriceWeb : listProductsTotalPriceWeb) {
			listProductsTotalPriceWebFormat.add(formatString(productPriceWeb));
		}
		System.out.println("Total price Web UI: " + listProductsTotalPriceWebFormat);

		// Compare values
		Assert.assertEquals(listProductsTotalPrice, listProductsTotalPriceWebFormat);

		// Get Subtotal price displayed in Web UI
		int subtotalPriceWeb = formatString(shoppingCartPage.getSubtotalPrice());
		System.out.println("Subtotal price Web UI: " + subtotalPriceWeb);

		// Sum of all products total price
		int sumOfTotalPrice = 0;
		for (int i : listProductsTotalPriceWebFormat) {
			sumOfTotalPrice += i;
		}
		System.out.println("Subtotal price caculate: " + sumOfTotalPrice);

		// Compare values
		Assert.assertEquals(sumOfTotalPrice, subtotalPriceWeb);
	}

	@AfterMethod
	public void afterClass() {
		removeBrowserDriver();
	}

	public int formatString(String s) {
		int price = Integer.parseInt(s.trim().replace("$", "").replace(".00", "").replace(",", ""));
		return price;
	}

	public int randomNumber(int a) {
		Random rand = new Random();
		return rand.nextInt(a);
	}
}
