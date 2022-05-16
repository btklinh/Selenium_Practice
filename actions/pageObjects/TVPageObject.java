package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.TVPageUI;

public class TVPageObject extends AbstractPage{
	
	WebDriver driver;

	public TVPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToAddToWishList() {
		clickToElement(driver, TVPageUI.ADD_TO_WISH_LIST_LINK);
		
	}

	

	

}
