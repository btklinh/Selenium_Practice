package com.techpanda.cart;

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

public class TC_01_Add_Product_To_Cart extends AbstractTest {

	WebDriver driver;
	HomePageObject homePage;
	MobilePageObject mobilePage;
	ShoppingCartPageObject shoppingCartPage;
	TVPageObject tvPage;
	int productPriceInList, productPriceInCart, productQtyInCart, productCartTotal;
	int product2PriceInList, product2PriceInCart, product2QtyInCart, product2CartTotal;
	int subtotal, grandtotal, tax;
	String productName1, productName2;
	ProductDetailPageObject productDetailPage;
	List<String> listProductsName;
	int position;

	@Parameters("browser")
	@BeforeMethod
	public void beforeClass(String browser) {
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

	@Test
	public void TC_01_Add_One_Product() {
		System.out.println("Step 01 - Click on Mobile link");
		openPageAndGetListProducts("Mobile");

		System.out.println("Step 02 - Verify price of a product on product list page");
		productName1 = listProductsName.get(position);
		productPriceInList = formatString(mobilePage.getProductPriceByPosition(driver, String.valueOf(position + 1)));

		System.out.println("Step 03 - Add a product to cart");
		shoppingCartPage = mobilePage.clickToAddToCartButtonInListByPosition(driver, String.valueOf(position + 1));

		System.out.println("Step 04 - Verify name, price, quantity, subtotal of product");
		productPriceInCart = formatString(shoppingCartPage.getPriceOnCartByProductName(productName1));
		productQtyInCart = Integer.parseInt(shoppingCartPage.getQtyOnCartByProductName(productName1));
		productCartTotal = formatString(shoppingCartPage.getProductCartTotalByProductName(productName1));

		Assert.assertEquals(productPriceInCart, productPriceInList);
		Assert.assertEquals(productQtyInCart, 1);
		Assert.assertEquals(productCartTotal, productPriceInCart * productQtyInCart);
	}

	@Test
	public void TC_02_Verify_Flat_Rate_Grand_Total() {

		TC_01_Add_One_Product();

		System.out.println("Step 05 - Click on TV link");
		openPageAndGetListProducts("TV");

		System.out.println("Step 06 - Verify a product price on list product page");
		productName2 = listProductsName.get(position);
		product2PriceInList = formatString(tvPage.getProductPriceByPosition(driver, String.valueOf(position + 1)));

		System.out.println("Step 07 - Add a TV product to cart");
		shoppingCartPage = tvPage.clickToAddToCartButtonInListByPosition(driver, String.valueOf(position + 1));

		productPriceInCart += formatString(shoppingCartPage.getPriceOnCartByProductName(productName2));
		productQtyInCart += Integer.parseInt(shoppingCartPage.getQtyOnCartByProductName(productName2));
		productCartTotal += formatString(shoppingCartPage.getProductCartTotalByProductName(productName2));

		System.out.println("Step 08 - Estimate Tax");
		shoppingCartPage.selectCountryDropbox("United States");
		shoppingCartPage.selectStateDropbox("Alabama");
		shoppingCartPage.inputToZipText("100106");
		shoppingCartPage.clickToEstimateLink();

		System.out.println("Step 09 - Select Tax and click Update total");
		shoppingCartPage.selectTax();
		shoppingCartPage.clickToUpdateTotalButton();

		subtotal = formatString(shoppingCartPage.getSubtotalPrice());
		grandtotal = formatString(shoppingCartPage.getGrandTotalPrice());
		tax = formatString(shoppingCartPage.getFlatRate());

		System.out.println("Step 10 - Verify Grand Total price after update tax value");
		Assert.assertEquals(subtotal, productPriceInCart);
		Assert.assertEquals(subtotal + tax, grandtotal);
	}

	@Test
	public void TC_03_Add_Multi_Product() throws InterruptedException {
		System.out.println("Step 01 - Click on Mobile link");
		openPageAndGetListProducts("Mobile");

		System.out.println("Step 02 - Add a product to cart");
		shoppingCartPage = mobilePage.clickToAddToCartButtonInListByPosition(driver, String.valueOf(position + 1));

		System.out.println("Step 03 - Click to TV link");
		openPageAndGetListProducts("TV");

		System.out.println("Step 04 - Add a TV to cart");
		shoppingCartPage = tvPage.clickToAddToCartButtonInListByPosition(driver, String.valueOf(position + 1));

		System.out.println("Step 05 - Click on Mobile link");
		openPageAndGetListProducts("Mobile");

		System.out.println("Step 06 - Add a product to cart");
		shoppingCartPage = mobilePage.clickToAddToCartButtonInListByPosition(driver, String.valueOf(position + 1));

	}

	public void TC_04_Add_Product_In_Detail_Page() throws InterruptedException {
		openPageAndGetListProducts("Mobile");

		productPriceInList = formatString(mobilePage.getMobilePriceByPosition(String.valueOf(position + 1)));

		System.out.println("Step 01: Click to product name to view detail of product");
		productDetailPage = mobilePage.clickToProductName(driver, String.valueOf(position + 1));
		// Thread.sleep(3000);

		System.out.println("Step 02: Update Qty to 4");
		productDetailPage.inputQuantityTextbox("4");

		System.out.println("Step 03: Click to Add to Cart button");
		shoppingCartPage = productDetailPage.clickToAddToCartButton();

		System.out.println("Step 04 - Verify name, price, quantity, subtotal of product");
		productPriceInCart = formatString(shoppingCartPage.getPriceOnCartByProductName(productName1));
		productQtyInCart = Integer.parseInt(shoppingCartPage.getQtyOnCartByProductName(productName1));
		productCartTotal = formatString(shoppingCartPage.getProductCartTotalByProductName(productName1));

		System.out.println("---Number of product in shopping cart: " + productQtyInCart + "---");
		System.out.println("---Product price total in shopping cart: " + productCartTotal + "---");

		Assert.assertEquals(productPriceInCart, productPriceInList);
		Assert.assertEquals(productQtyInCart, 4);
		Assert.assertEquals(productCartTotal, productPriceInCart * productQtyInCart);

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
