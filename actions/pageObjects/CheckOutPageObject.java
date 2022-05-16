package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.CheckOutPageUI;


public class CheckOutPageObject extends AbstractPage {

	WebDriver driver;

	public CheckOutPageObject(WebDriver driver) {
		// super();
		this.driver = driver;
	}

	public void selectNewAddressDropbox() {
		selectItemInDropdown(driver, CheckOutPageUI.SELECT_ADDRESS_DROPBOX, "New Address");
	}

	public void inputToAddressText(String address) {
		sendKeysToElement(driver, CheckOutPageUI.ADDRESS_TEXT, address);

	}

	public void selectCountryDropbox(String country) {
		selectItemInDropdown(driver, CheckOutPageUI.COUNTRY_DROPBOX, country);

	}

	public void selectStateDropbox(String state) {
		selectItemInDropdown(driver, CheckOutPageUI.STATE_DROPBOX, state);
	}

	public void inputToCityText(String city) {
		// TODO Auto-generated method stub
		sendKeysToElement(driver, CheckOutPageUI.CITY_TEXT, city);
	}

	public void inputToZipText(String zip) {
		// TODO Auto-generated method stub
		sendKeysToElement(driver, CheckOutPageUI.ZIP_TEXT, zip);
	}

	public void inputToTelephoneText(String telephone) {
		sendKeysToElement(driver, CheckOutPageUI.TELEPHONE_TEXT, telephone);

	}

	public void selectShipToThisAddress() {
		// TODO Auto-generated method stub
		checkToCheckbox(driver, CheckOutPageUI.SHIP_TO_THIS_ADDRESS_RADIO);
	}

	public void clickToShipContinueButton() {
		clickToElement(driver, CheckOutPageUI.BILLING_CONTINUE_BUTTON);

	}

	public String getFlatRate() {

		return getElementText(driver, CheckOutPageUI.FLAT_RATE_TEXT);
	}

	public void clickToShippingMethodContinueButton() {
		clickToElement(driver, CheckOutPageUI.SHIPPING_METHOD_CONTINUE_BUTTON);
	}

	public void selectCheckMoneyOrder() {
		checkToCheckbox(driver, CheckOutPageUI.CHECK_MONEY_ORDER_RADIO);
	}

	public void clickToPaymentSaveContinueButton() {
		clickToElement(driver, CheckOutPageUI.PAYMENT_SAVE_CONTINUE_BUTTON);
	}

	public String getTotalPrice() {
		return getElementText(driver, CheckOutPageUI.GRAND_TOTAL_TEXT);
	}

	public void clickToPlaceOrderButton() {
		clickToElement(driver, CheckOutPageUI.PLACE_ORDER_BUTTON);

	}

	public String getSucceedMessage() {
		sleepInSecond(2);
		return getElementText(driver, CheckOutPageUI.ORDER_SUCCEED_MESSAGE_TEXT);

	}

}
