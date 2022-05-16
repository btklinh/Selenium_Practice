package com.techpanda.noframework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Practice_03_TC_03 {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String pageUrl;
	private WebElement findElement(WebDriver driver, String locator) {
		this.driver = driver;
		return driver.findElement(By.xpath(locator));
	}
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Access live techpanda website
		driver.get("https://live.techpanda.org/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);			  
	}

	
	@Test
	public void TC_01_Verify_Error_Mess_Invalid_Qty() {
		//Click to Mobile link 
		findElement(driver, "//a[text()='Mobile']").click();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Click on ADD TO CART for Sony Xperia mobile
		findElement(driver, "//li[3]//button").click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Change QTY value to 1000 and click "UPDATE" button 
		findElement(driver, "//input[@title='Qty']").clear();
		findElement(driver, "//input[@title='Qty']").sendKeys("1000");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		findElement(driver, "//button[@title='Update']").click();
		
		//Check message error displayed				
		String error = findElement(driver, "//p[@class='item-msg error']").getText();
		String exError = "* The maximum quantity allowed for purchase is 500.";
		Assert.assertEquals(error.compareTo(exError),0);
	}
	
	@Test
	public void TC_02_Verify_Mess_Empty_Card() {
		// Click 'EMPTY CART' link 
		findElement(driver, "//button[@id='empty_cart_button']").click();
		String mess = findElement(driver, "//div[@class='cart-empty']/p[1]").getText();
		String exMess = "YOU have no items in your shopping cart.";
		Assert.assertEquals(mess.compareTo(exMess),0);
		// Message "You have no items in your shopping cart." is shown
	}
		

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
