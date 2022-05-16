package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.ProductDetailPageUI;

public class ProductDetailPageObject extends AbstractPage {
	WebDriver driver;

	public ProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getPriceOfProduct() {
		return getElementText(driver, ProductDetailPageUI.SONY_PRICE_TEXT);
	}

	public void inputQuantityTextbox(String quantity) {
		waitToElementVisible(driver, ProductDetailPageUI.QUANTITY_TEXTBOX);
		sendKeysToElement(driver, ProductDetailPageUI.QUANTITY_TEXTBOX, quantity);
		
	}

	public ShoppingCartPageObject clickToAddToCartButton() {
		waitToElementClickable(driver, ProductDetailPageUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, ProductDetailPageUI.ADD_TO_CART_BUTTON);
		return PageGeneratorManager.getShoppingCartPage(driver);
	}

}
