package com.techpanda.noframework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.HomePageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.ShoppingCartPageObject;

public class NewTest extends AbstractTest {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String pageUrl;
	HomePageObject homePage;
	MyAccountPageObject myAccountPage;
	ShoppingCartPageObject shoppingCartPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver.exe");
		driver = new FirefoxDriver();

		// Access live techpanda website
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void Login() {
		myAccountPage = new MyAccountPageObject(driver);
		myAccountPage.inputLoginEmailText("linhbui1993@gmail.com");
		myAccountPage.inputLoginPasswordText("11111111");
		myAccountPage.clickToLoginButton();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		myAccountPage.clickToElement(driver, "//span[text()='Cart']");
		
		myAccountPage.waitToElementVisible(driver, "//a[@class='cart-link']");
		myAccountPage.clickToElement(driver,"//a[@class='cart-link']");
		shoppingCartPage = new ShoppingCartPageObject(driver);
		
		System.out.println("Lay danh sach ten san pham trong gio hang");
		List<String> productNameList = new ArrayList<String>();
		productNameList = shoppingCartPage.getElementsText(driver, "//h2[@class='product-name']/a");
		System.out.print("\n---Danh sach san pham---" );
		for (String productName : productNameList) {
			System.out.print("\n" + productName);
		}
		
		System.out.println("\n Lay danh sach gia tung san pham trong gio hang");
		List<String> productPriceList = new ArrayList<String>();
		productPriceList = shoppingCartPage.getElementsText(driver, "//td[@class='product-cart-price']//span[@class='price']");
		System.out.print("---Danh sach gia tung san pham---" );
		List<Integer> productPriceListFormat = new ArrayList<Integer>();
		for (String productPrice : productPriceList) {
			productPriceListFormat.add(formatString(productPrice));			
		}
		System.out.print("\n" + productPriceListFormat);
		
		System.out.println("\n Lay danh sach so luong tung san pham trong gio hang");
		List<String> productQuantityList = new ArrayList<String>();
		productQuantityList = shoppingCartPage.getElementsAttribute(driver, "//td[@class='product-cart-actions']/input", "value");
		System.out.print("---Danh sach so luong tung san pham---" );
		List<Integer> productQuantityListFormat = new ArrayList<Integer>();
		for (String productQuantity : productQuantityList) {
			productQuantityListFormat.add(Integer.parseInt(productQuantity));			
		}
		System.out.print("\n" + productQuantityListFormat);
		
		System.out.println("\n Lay danh sach tong gia tung san pham trong gio hang");
		List<String> productPriceTotalList = new ArrayList<String>();
		productPriceTotalList = shoppingCartPage.getElementsText(driver, "//td[@class='product-cart-total']//span[@class='price']");
		System.out.print("---Danh sach tong gia tung san pham---" );
		List<Integer> productPriceTotalListFormat = new ArrayList<Integer>();
		for (String productPriceTotal : productPriceTotalList) {
			productPriceTotalListFormat.add(formatString(productPriceTotal));			
		}
		System.out.print("\n" + productPriceTotalListFormat);
		
		List<Integer> productPriceTotalList2 = new ArrayList<Integer>();
		for (int i=0; i<productPriceListFormat.size(); i++) {			
			productPriceTotalList2.add(productPriceListFormat.get(i)*productQuantityListFormat.get(i));			
		}
		System.out.print("\n" + productPriceTotalList2);
		
		Assert.assertEquals(productPriceTotalListFormat, productPriceTotalList2);
	}

	public void Test() {
		homePage = new HomePageObject(driver);
		// = homePage.getElementText(driver, "//li[3]//h2[@class='product-name']/a");
		String mobileName = homePage.getElementAttribute(driver, "//li[3]//h2[@class='product-name']/a", "title");
		System.out.println(mobileName);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
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