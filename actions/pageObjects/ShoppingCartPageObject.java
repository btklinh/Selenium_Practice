package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.ShoppingCartPageUI;

public class ShoppingCartPageObject extends AbstractPage {
	WebDriver driver;

	public ShoppingCartPageObject(WebDriver driver) {
		//super();
		this.driver = driver;
	}

	public String getGrandTotalPrice() {
		return getElementText(driver, ShoppingCartPageUI.GRAND_TOTAL_TEXT);
	}

	public void clickToProceedToCheckOutButton() {
		clickToElement(driver, ShoppingCartPageUI.PROCEED_TO_CHECKOUT_BUTTON);
		
	}

	public String getSubtotalPrice() {
		
		return getElementText(driver,ShoppingCartPageUI.SUBTOTAL_CART_TEXT);
	}

	public String getQty() {

		return getElementAttribute(driver, ShoppingCartPageUI.QUANTITY_TEXT, "value");
	}

	public void inputToQtyTextBox(int quantity) {
		sendKeysToElement(driver, ShoppingCartPageUI.QUANTITY_TEXT, Integer.toString(quantity));
	}

	public void clickToUpdateButton() {
		clickToElement(driver, ShoppingCartPageUI.UPDATE_BUTTON);
		// TODO Auto-generated method stub
		
	}



	public void selectCountryDropbox(String country) {
		waitToElementVisible(driver, ShoppingCartPageUI.COUNTRY_DROP_BOX);
		selectItemInDropdown(driver, ShoppingCartPageUI.COUNTRY_DROP_BOX, country);
	}

	public void selectStateDropbox(String state) {
		waitToElementVisible(driver, ShoppingCartPageUI.STATE_DROP_BOX);
		selectItemInDropdown(driver, ShoppingCartPageUI.STATE_DROP_BOX, state);
	}

	public void inputToZipText(String zip) {
		waitToElementVisible(driver, ShoppingCartPageUI.ZIP_TEXT_BOX);
		sendKeysToElement(driver, ShoppingCartPageUI.ZIP_TEXT_BOX, zip);
	}

	public void clickToEstimateLink() {
		waitToElementClickable(driver, ShoppingCartPageUI.ESTIMATE_LINK);
		clickToElement(driver, ShoppingCartPageUI.ESTIMATE_LINK);
	}

	public String getFlatRate() {
		waitToElementVisible(driver, ShoppingCartPageUI.FLAT_RATE_TEXT);
		return getElementText(driver, ShoppingCartPageUI.FLAT_RATE_TEXT);
	}

	public void clickToUpdateTotalButton() {
		waitToElementVisible(driver, ShoppingCartPageUI.UPDATE_TOTAL_BUTTON);
		clickToElement(driver, ShoppingCartPageUI.UPDATE_TOTAL_BUTTON);
	}

	public String getPriceOnCartByProductName(String productName) {
		waitToElementVisible(driver, ShoppingCartPageUI.DYNAMIC_PRICE_BY_PRODUCT_NAME, productName);
		return getElementText(driver, ShoppingCartPageUI.DYNAMIC_PRICE_BY_PRODUCT_NAME, productName);
	}

	public String getQtyOnCartByProductName(String productName) {
		waitToElementVisible(driver, ShoppingCartPageUI.DYNAMIC_QTY_BY_PRODUCT_NAME, productName);
		return getElementAttribute(driver, ShoppingCartPageUI.DYNAMIC_QTY_BY_PRODUCT_NAME, "value", productName);
	}

	public String getProductCartTotalByProductName(String productName) {
		waitToElementVisible(driver, ShoppingCartPageUI.DYNAMIC_SUBTOTAL_BY_PRODUCT_NAME, productName);
		return getElementText(driver, ShoppingCartPageUI.DYNAMIC_SUBTOTAL_BY_PRODUCT_NAME, productName);
	}

	
	public void selectTax() {
		waitToElementClickable(driver, ShoppingCartPageUI.FLAT_RATE_CHECKBOX);
		
		checkToCheckbox(driver, ShoppingCartPageUI.FLAT_RATE_CHECKBOX);
	}

	public String getAddProductText() {
		waitToElementVisible(driver, ShoppingCartPageUI.ADD_PRODUCT_MESSAGE_TEXT);
		return getElementText(driver, ShoppingCartPageUI.ADD_PRODUCT_MESSAGE_TEXT);
	}

	public List<String> getProductNameList() {
		waitToElementVisible(driver, ShoppingCartPageUI.PRODUCT_NAME_TEXT_LIST);
		return getElementsText(driver, ShoppingCartPageUI.PRODUCT_NAME_TEXT_LIST);
	}

	public List<Integer> getProductQuantityList() {
		waitToElementVisible(driver,ShoppingCartPageUI.PRODUCT_QUANTITY_TEXT_LIST);
		return getElementsAttributeInt(driver, ShoppingCartPageUI.PRODUCT_QUANTITY_TEXT_LIST, "value") ;
	}

	public List<String> getProductPriceList() {
		waitToElementVisible(driver,ShoppingCartPageUI.PRODUCT_PRICE_TEXT_LIST);
		return getElementsText(driver, ShoppingCartPageUI.PRODUCT_PRICE_TEXT_LIST);
	}

	public List<String> getTotalPrice() {
		waitToElementVisible(driver,ShoppingCartPageUI.PRODUCT_TOTALPRICE_TEXT_LIST);
		return getElementsText(driver, ShoppingCartPageUI.PRODUCT_TOTALPRICE_TEXT_LIST);
	}

	public void inputToDiscountCodesTextbox(String code) {
		waitToElementVisible(driver,ShoppingCartPageUI.DISCOUNT_CODES_TEXT_BOX);
		sendKeysToElement(driver, ShoppingCartPageUI.DISCOUNT_CODES_TEXT_BOX, code);
	}

	public void clickToApplyLink() {
		waitToElementVisible(driver,ShoppingCartPageUI.DISCOUNT_CODES_APPLY_BUTTON);
		clickToElement(driver, ShoppingCartPageUI.DISCOUNT_CODES_APPLY_BUTTON);
	}
	
	

}
