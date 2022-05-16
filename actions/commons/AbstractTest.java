package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {

	WebDriver driver;
//	String projectPath = System.getProperty("user.dir");

	protected enum Browser {
		CHROME, FIREFOX, IE, EDGE, SAFARI, OPERA;
	}

	public WebDriver getBrowserDriver(String browserName) {
		Browser broswser = Browser.valueOf(browserName.toUpperCase());

		switch (broswser) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
			
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
			
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
			
		default:
			throw new RuntimeException("Please choose browser name");
		}

		// Access live techpanda website
		driver.get(GlobalConstants.TECHPANDA_DEV_URL);
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}
	
	public void removeBrowserDriver() {
		driver.quit();
	}

}
