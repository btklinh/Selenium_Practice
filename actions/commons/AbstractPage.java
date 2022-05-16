package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.HomePageObject;
import pageObjects.ProductDetailPageObject;
import pageObjects.ShoppingCartPageObject;
import pageUI.MobilePageUI;

public abstract class AbstractPage {

	private Alert alert;
	private WebDriverWait explicitWait;
	private Select select;
	private JavascriptExecutor jsExecutor;
	private Actions action;
	private long longTimeout = 30;

	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void waitToAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		alert = driver.switchTo().alert();
		alert.sendKeys(value);
	}

	public String getTextInAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement find(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));
	}

	public List<WebElement> finds(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}

	public List<String> getElementsText(WebDriver driver, String locator) {
		List<WebElement> element = driver.findElements(byXpath(locator));
		List<String> elementList = new ArrayList<String>();
		for (WebElement p : element) {
			elementList.add(String.valueOf(p.getText().trim()));
		}
		return elementList;
	}

	public List<String> getElementsText(WebDriver driver, String locator, String... values) {
		List<WebElement> element = driver.findElements(byXpath(castToRestParameter(locator, values)));
		List<String> elementList = new ArrayList<String>();
		for (WebElement p : element) {
			elementList.add(String.valueOf(p.getText().trim()));
		}
		return elementList;
	}
	public List<String> getElementsAttribute(WebDriver driver, String locator, String attribute) {
		List<WebElement> element = driver.findElements(byXpath(locator));
		List<String> elementList = new ArrayList<String>();
		for (WebElement p : element) {
			elementList.add(String.valueOf(p.getAttribute(attribute)));
		}
		return elementList;
	}
	
	public List<Integer> getElementsAttributeInt(WebDriver driver, String locator, String attribute) {
		List<WebElement> element = driver.findElements(byXpath(locator));
		List<Integer> elementList = new ArrayList<Integer>();
		for (WebElement p : element) {
			elementList.add(Integer.parseInt(p.getAttribute(attribute)));
		}
		return elementList;
	}
	
	public List<String> getElementsAttribute(WebDriver driver, String locator, String attribute, String... values) {
		List<WebElement> element = driver.findElements(byXpath(castToRestParameter(locator, values)));
		List<String> elementList = new ArrayList<String>();
		for (WebElement p : element) {
			elementList.add(String.valueOf(p.getAttribute(attribute)));
			System.out.println(p.getAttribute(attribute));
		}
		return elementList;
	}

	public void clickToElement(WebDriver driver, String locator) {
		find(driver, locator).click();
	}

	public void sendKeysToElement(WebDriver driver, String locator, String value) {
		find(driver, locator).clear();
		find(driver, locator).sendKeys(value);
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String itemValue) {
		select = new Select(find(driver, locator));
		select.selectByVisibleText(itemValue);
	}

	public String getFirstSelectedItemInDropdown(WebDriver driver, String locator) {
		select = new Select(find(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(find(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		find(driver, parentLocator);
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childItemLocator)));
		List<WebElement> allItems = finds(driver, childItemLocator);

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void sleepInSecond(long timeout) {

		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return find(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... values) {
		return find(driver, castToRestParameter(locator, values)).getAttribute(attributeName);
	}


	public String getElementText(WebDriver driver, String locator) {

		return find(driver, locator).getText();
	}

	public String getElementText(WebDriver driver, String locator, String... values) {
		
		return find(driver, castToRestParameter(locator, values)).getText();
	}

	public int countElementSize(WebDriver driver, String locator) {
		return finds(driver, locator).size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		if (!find(driver, locator).isSelected()) {
			find(driver, locator).click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		if (find(driver, locator).isSelected()) {
			find(driver, locator).click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		return find(driver, locator).isDisplayed();
	}

	public boolean isControlEnabled(WebDriver driver, String locator) {
		return find(driver, locator).isEnabled();
	}

	public boolean isControlSelected(WebDriver driver, String locator) {
		return find(driver, locator).isSelected();
	}

	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(find(driver, locator));
	}

	public void switchToDefaultPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(find(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(find(driver, locator)).perform();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(find(driver, locator)).perform();
	}

	public void dragAndDropToElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(find(driver, sourceLocator), find(driver, targetLocator)).perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(find(driver, locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = find(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", find(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", find(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", find(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", find(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver, int timeout) {
		explicitWait = new WebDriverWait(driver, timeout);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", find(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", find(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitToElementPresence(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(locator)));
	}

	public void waitToElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}
	
	public void waitToElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToRestParameter(locator, values))));
	}

	public void waitToElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}

	public void waitToElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}

	public String castToRestParameter(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return locator;
	}

	public AbstractPage openMenuPageByName(WebDriver driver, String pageName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, pageName);
		switch (pageName) {
		case "Mobile":
			return PageGeneratorManager.getMobilePage(driver);
		case "TV":
			return PageGeneratorManager.getTVPage(driver);
		case "Home":
			return PageGeneratorManager.getHomePage(driver);
		case "My Account":
			return PageGeneratorManager.getMyAccountPage(driver);
		default:
			throw new RuntimeException();
		}
	}
	
	public ShoppingCartPageObject clickToAddToCartButton(WebDriver driver, String productName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_ADD_TO_CART_BUTTON_IN_LIST_BY_PRODUCT_NAME, productName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_ADD_TO_CART_BUTTON_IN_LIST_BY_PRODUCT_NAME, productName);
		return PageGeneratorManager.getShoppingCartPage(driver);
	}
	
	public ShoppingCartPageObject clickToAddToCartButtonInListByPosition(WebDriver driver, String position) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_ADD_TO_CART_BUTTON_IN_LIST_BY_POSITION, position);
		clickToElement(driver, AbstractPageUI.DYNAMIC_ADD_TO_CART_BUTTON_IN_LIST_BY_POSITION, position);
		return PageGeneratorManager.getShoppingCartPage(driver);
	}

	public void waitToElementClickable(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToRestParameter(locator, values))));
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		find(driver, castToRestParameter(locator, values)).click();
	}

	public List<String> getProductsNameInListByPosition(WebDriver driver, String attributeValue){
		waitToElementVisible(driver, AbstractPageUI.PRODUCT_NAME_TEXT_LIST);
		return getElementsAttribute(driver, AbstractPageUI.PRODUCT_NAME_TEXT_LIST, attributeValue);		
	}
	
	public ProductDetailPageObject clickToProductName(WebDriver driver, String index) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_PRODUCT_NAME_TEXT_BY_POSITION, index);
		clickToElement(driver, AbstractPageUI.DYNAMIC_PRODUCT_NAME_TEXT_BY_POSITION, index);
		return PageGeneratorManager.getProductDetailPage(driver);
	}
	
	public String getProductPriceByPosition(WebDriver driver, String position) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_PRODUCT_PRICE_TEXT_BY_POSITION, position);
		return getElementText(driver, AbstractPageUI.DYNAMIC_PRODUCT_PRICE_TEXT_BY_POSITION, position);
	}

	public void clickToContentMenuByMenuName(WebDriver driver, String menuName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, menuName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, menuName);
		//return menuName;
	}
	
	public String getPageHeader(WebDriver driver) {
		waitToElementVisible(driver, AbstractPageUI.PAGE_TITLE_TEXT);
		return getElementText(driver, AbstractPageUI.PAGE_TITLE_TEXT);
	}
	
	public HomePageObject navigateToHomePage(WebDriver driver) {
		driver.get(GlobalConstants.TECHPANDA_DEV_URL);
		return PageGeneratorManager.getHomePage(driver);
	}
	
	public void clickToHeaderAccountLink(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.HEADER_ACCOUNT_LINK);
		clickToElement(driver, AbstractPageUI.HEADER_ACCOUNT_LINK);
	}

	public List<String> getMenuListWhenClickAccountLink(WebDriver driver) {
		waitToElementVisible(driver, AbstractPageUI.HEADER_ACCOUNT_SUBMENU_BORDER);
		return getElementsText(driver, AbstractPageUI.HEADER_ACCOUNT_SUBMENU);
	}

	public void clickToHeaderMenuLinkByMenuName(WebDriver driver, String menuName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_ACCOUNT_SUBMENU_BY_MENU_NAME, menuName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_ACCOUNT_SUBMENU_BY_MENU_NAME, menuName);
	}
}
