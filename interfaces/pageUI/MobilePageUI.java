package pageUI;

public class MobilePageUI {

	public static final String PRODUCT_NAME_TEXT = "//h2[@class='product-name']";
	public static final String SORT_BY_NAME_SLBOX = "//div[@class='toolbar-bottom']//div[@class='sorter']//select[@title='Sort By']";
	public static final String PRICE_TEXT = "//span[@class='price'][not(contains(@id,'old-price'))]";
	public static final String RATE_TEXT = "//div[@class='rating-box']/div";
	public static final String SONY_PRICE_TEXT ="//span[@id='product-price-1']";
	public static final String SONY_TITLE_TEXT = "//a[text()='Sony Xperia']";
	public static final String DYNAMIC_PRODUCT_PRICE_BY_PRODUCT_NAME = "//a[text()='%s']/parent::h2/following-sibling::div[@class='price-box']//span[@class='price' and not(contains(@id,'old-price'))]";
	public static final String DYNAMIC_ADD_TO_CART_BY_PRODUCT_NAME = "//a[text()='%s']/parent::h2/following-sibling::div[@class='actions']/button";
	public static final String DYNAMIC_PRODUCT_PRICE_BY_POSITION = "//li[@class='item last' and position()='%s']//span[@class='price' and not(contains(@id,'old-price'))]";
	
}
