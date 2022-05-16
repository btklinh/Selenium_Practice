package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.MyWishListPageUI;

public class MyWishListPageObject extends AbstractPage {
	
	WebDriver driver;

	public MyWishListPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToShareWishListLink() {
		clickToElement(driver, MyWishListPageUI.SHARE_WISH_LIST_LINK);
		
	}

	public void inputToEmailText(String shareEmail) {
		sendKeysToElement(driver, MyWishListPageUI.EMAIL_TEXT, shareEmail);
		
	}

	public void inputToMessageText(String shareMessage) {
		sendKeysToElement(driver, MyWishListPageUI.SHARE_MESSAGE_TEXT, shareMessage);
		
	}

	public void clickToShareWishListButton() {
		clickToElement(driver, MyWishListPageUI.SHARE_WISH_LIST_BUTTON);
	}
	
	public String getShareMessageText() {
		return getElementText(driver, MyWishListPageUI.SHARE_MESSAGE_SUCEED_TEXT);
		
	}

	public void clickToAddToCartButton() {
		clickToElement(driver, MyWishListPageUI.ADD_TO_CART_BUTTON);
		
	}

}
