package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.AbstractPageUI;
import commons.PageGeneratorManager;
import pageUI.MobilePageUI;

public class MobilePageObject extends AbstractPage {

	WebDriver driver;

	public MobilePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public List<String> getAllProductName() {
		List<String> productNameList = getElementsText(driver, MobilePageUI.PRODUCT_NAME_TEXT);
//		for (String p : productNameList) {
//			System.out.println(p + ",");
//		}
		return productNameList;
	}

	public void sortByType(List<String> productType) {
		Collections.sort(productType);
	}

	public void sortByReview(List<Integer> productType) {
		Collections.sort(productType);
	}

	public void selectSortByType(String type) {
		waitToElementVisible(driver, MobilePageUI.SORT_BY_NAME_SLBOX);
		selectItemInDropdown(driver, MobilePageUI.SORT_BY_NAME_SLBOX, type);
	}

	public List<String> getAllMobilePrice() {
		List<String> productPriceList = getElementsText(driver, MobilePageUI.PRICE_TEXT);
//		for (String p : productPriceList) {
//			System.out.println(p + ",");
//		}
		return productPriceList;
	}

	public List<Integer> getAllMobileRate() {
		List<String> productRateList = getElementsAttribute(driver, MobilePageUI.RATE_TEXT, "style");
		List<Integer> convert = new ArrayList<Integer>();
		for (String p : productRateList) {
			int s = Integer.parseInt(p.replace("width: ", "").replace("%;", ""));
			convert.add(s);
			//System.out.println(s);
		}
		return convert;
	}

	public String getPriceOfProduct() {
		return getElementText(driver, MobilePageUI.SONY_PRICE_TEXT);
	}

	public void clickToSonyTitle() {
		clickToElement(driver, MobilePageUI.SONY_TITLE_TEXT);
	}

	public String getMobilePrice(String productName) {
		waitToElementVisible(driver, MobilePageUI.DYNAMIC_PRODUCT_PRICE_BY_PRODUCT_NAME, productName);
		return getElementText(driver, MobilePageUI.DYNAMIC_PRODUCT_PRICE_BY_PRODUCT_NAME, productName);
	}
	
	public String getMobilePriceByPosition(String position) {
		waitToElementVisible(driver, MobilePageUI.DYNAMIC_PRODUCT_PRICE_BY_POSITION, position);
		return getElementText(driver, MobilePageUI.DYNAMIC_PRODUCT_PRICE_BY_POSITION, position);
	}
	

	

//	public ShoppingCartPageObject clickToAddToCartButton(WebDriver driver, String productName) {
//		waitToElementClickable(driver, MobilePageUI.DYNAMIC_ADD_TO_CART_BY_PRODUCT_NAME, productName);
//		clickToElement(driver, MobilePageUI.DYNAMIC_ADD_TO_CART_BY_PRODUCT_NAME, productName);
//		return PageGeneratorManager.getShoppingCartPage(driver);
//	}
	
	



}
