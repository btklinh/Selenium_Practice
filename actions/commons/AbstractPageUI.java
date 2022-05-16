package commons;

public class AbstractPageUI {

	public static final String TV_PAGE_LINK = "//a[text()='TV']";
	public static final String MOBILE_PAGE_LINK = "//a[text()='Mobile']";
	public static final String HOME_PAGE_LINK = "//a[text()='Home']";

	public static final String DYNAMIC_ADD_TO_CART_BUTTON_IN_LIST_BY_PRODUCT_NAME = "//a[text()='%s']/parent::h2/following-sibling::div[@class='actions']/button";
	public static final String DYNAMIC_ADD_TO_CART_BUTTON_IN_LIST_BY_POSITION = "//li[@class='item last' and position()='%s']//button[@class='button btn-cart']";
	
	public static final String DYNAMIC_MENU_PAGE_LINK = "//a[text()='%s']";
	public static final String DYNAMIC_PRODUCT_NAME_TEXT_BY_POSITION = "//li[@class='item last' and position()='%s']//h2[@class='product-name']/a";
	public static final String DYNAMIC_PRODUCT_PRICE_TEXT_BY_POSITION = "//li[@class='item last' and position()='%s']//span[@class='price' and not(contains(@id,'old-price'))]";
	
	public static final String PRODUCT_NAME_TEXT_LIST = "//li[@class='item last']//h2[@class='product-name']/a";
	public static final String HEADER_ACCOUNT_SUBMENU_BORDER = "//div[@id='header-account']";
	public static final String HEADER_ACCOUNT_SUBMENU = "//div[@id ='header-account']//a";
	public static final String HEADER_ACCOUNT_LINK= "//div[@class='account-cart-wrapper']//span[text()='Account']";
	public static final String DYNAMIC_ACCOUNT_SUBMENU_BY_MENU_NAME="//div[@id ='header-account']//a[contains(text(),'%s')]";
	public static final String PAGE_TITLE_TEXT = "//div[@class='page-title']/h1";
	
}
