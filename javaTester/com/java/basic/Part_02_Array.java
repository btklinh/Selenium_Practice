package com.java.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Part_02_Array extends AbstractPage{
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://live.techpanda.org/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Verify_Info() throws InterruptedException {
		System.out.println("Step 01 - Click to My Account");		
		clickToElement(driver, "//div[@class='footer']//a[@title='My Account']");
		
		waitToElementVisible(driver, "//input[@name='login[username]']");
		sendKeysToElement(driver, "//input[@name='login[username]']", "linhbui1993@gmail.com");
		
		waitToElementVisible(driver, "//input[@name='login[username]']");
		sendKeysToElement(driver, "//input[@name='login[password]']", "11111111");
		clickToElement(driver, "//button[@title='Login']");
		
		System.out.println("Step 02 - Click to Re-order Link");
		clickToElement(driver, "//tr[@class='first odd']//a[@class='link-reorder']");
		Thread.sleep(2000);
		
		
//		List<WebElement> subTotalPrice = driver.findElements(By.xpath("//td[@class='product-cart-price']//span[@class='price']"));
//		List<Integer> subTotalPriceString = new ArrayList<>();
//		for (WebElement p : subTotalPrice) {
//			subTotalPriceString.add(formatString(p.getText()));
//			System.out.println(formatString(p.getText()));
//		}
//		
//		List<WebElement> quantity = driver.findElements(By.xpath("//td[@class='product-cart-actions']//input"));
//		List<Integer> quantityString = new ArrayList<>();
//		for (WebElement p : quantity) {
//			int a = Integer.parseInt(p.getAttribute("value"));
//			quantityString.add(a);
//			System.out.println(a);
//		}
//		
//		List<Integer> sum = new ArrayList<Integer>();
//		for(int i=0; i< quantityString.size(); i++) {
//			int Total = quantityString.get(i)*subTotalPriceStringget(i);
//		}
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
