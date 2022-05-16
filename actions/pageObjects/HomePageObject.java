package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.HomePageUI;

public class HomePageObject extends AbstractPage{
	
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openUrl(String url) {		
		openUrl(driver, url);
	}

	public void clickToMobileLink() {
		clickToElement(driver, HomePageUI.MOBILE_LINK);
	}

	public String getHomePageTitle() {
		return getElementText(driver, HomePageUI.TITLE_TEXT);
	}

	public void clickToAccountMenu() {
		// TODO Auto-generated method stub
		
	}

	public void clickToMyAccountLink() {
		clickToElement(driver, HomePageUI.ACCOUNT_LINK);
		// TODO Auto-generated method stub
		
	}
	
	public MobilePageObject clickToMobileLink(WebDriver driver) {
		clickToElement(driver, HomePageUI.MOBILE_LINK);
		return PageGeneratorManager.getMobilePage(driver);
	}


}
