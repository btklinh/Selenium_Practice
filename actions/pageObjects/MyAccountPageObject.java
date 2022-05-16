package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.MyAccountPageUI;

public class MyAccountPageObject extends AbstractPage{
	
	WebDriver driver;

	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToCreateAnAccount() {
		clickToElement(driver, MyAccountPageUI.CREATE_ACCOUNT_LINK);
	}

	public void inputToFirstNameText(String firstName) {
		sendKeysToElement(driver, MyAccountPageUI.FIRST_NAME_TEXT, firstName);
		
	}

	public void inputToLastNameText(String lastName) {
		sendKeysToElement(driver, MyAccountPageUI.LAST_NAME_TEXT, lastName);
		
	}

	public void inputToEmailText(String email) {
		sendKeysToElement(driver, MyAccountPageUI.EMAIL_TEXT, email);
	}

	public void inputToPasswordText(String password) {
		sendKeysToElement(driver, MyAccountPageUI.PASSWORD_TEXT, password);
	}

	public void inputToConfirmPasswordText(String confirmPassword) {
		sendKeysToElement(driver, MyAccountPageUI.CONFIRM_PASSWORD_TEXT, confirmPassword);
	}

	public void clickToRegisterButton() {
		clickToElement(driver, MyAccountPageUI.REGISTER_BUTTON);
	}

	public String getSuceedRegisterMess() {
		return getElementText(driver, MyAccountPageUI.SUCEED_MESSAGE_TEXT);
	}

	public void clickToTVMenu() {
		clickToElement(driver, MyAccountPageUI.TV_LINK);
	}

	public void inputLoginEmailText(String email) {
		sendKeysToElement(driver, MyAccountPageUI.LOGIN_EMAIL_TEXT, email);
	}

	public void inputLoginPasswordText(String password) {
		sendKeysToElement(driver, MyAccountPageUI.LOGIN_PASSWORD_TEXT, password);
	}

	public void clickToLoginButton() {
		clickToElement(driver,MyAccountPageUI.LOGIN_BUTTON);
		
	}

	public void clickToMyWishListLink() {
		clickToElement(driver,MyAccountPageUI.MY_WISH_LIST_LINK);
	}

	public void clickToReorderLink() {
		clickToElement(driver, MyAccountPageUI.REORDER_LINK);
		
	}

}
