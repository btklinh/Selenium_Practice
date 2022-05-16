package pageUI;

public class ShoppingCartPageUI {
	
	public static final String GRAND_TOTAL_TEXT = "//strong[text()='Grand Total']/parent::td/following-sibling::td//span";
	public static final String PROCEED_TO_CHECKOUT_BUTTON="//ul[@class='checkout-types bottom']//button";
	public static final String SUBTOTAL_PRICE_TEXT = "//tr[@class='first odd']/td[3]//span[@class='price']";
	public static final String QUANTITY_TEXT = "//tr[@class='first odd']/td[4]/input";
	public static final String UPDATE_BUTTON = "//tr[@class='first odd']/td[4]/button";
	public static final String SUBTOTAL_CART_TEXT = "//td[contains(text(),'Subtotal')]/following-sibling::td/span";
	//public static final String TAX_TEXT = "//td[contains(text(),'Subtotal')]/following-sibling::td/span";
	public static final String FLAT_RATE_CHECKBOX = "//input[@id='s_method_flatrate_flatrate']";
	
	
	public static final String COUNTRY_DROP_BOX = "//select[@id='country']";	
	public static final String STATE_DROP_BOX = "//select[@id='region_id']";	
	public static final String ZIP_TEXT_BOX = "//input[@id='postcode']";	
	public static final String ESTIMATE_LINK = "//span[text()='Estimate']";	
	public static final String FLAT_RATE_TEXT = "//label[@for='s_method_flatrate_flatrate']/span";	
	public static final String UPDATE_TOTAL_BUTTON = "//button[@title='Update Total']";	
	public static final String DYNAMIC_PRICE_BY_PRODUCT_NAME = "//a[text()='%s']/parent::h2/parent::td/following-sibling::td[@class='product-cart-price']//span[@class='price']";	
	public static final String DYNAMIC_QTY_BY_PRODUCT_NAME = "//a[text()='%s']/parent::h2/parent::td/following-sibling::td[@class='product-cart-actions']/input";	
	public static final String DYNAMIC_SUBTOTAL_BY_PRODUCT_NAME = "//a[text()='%s']/parent::h2/parent::td/following-sibling::td[@class='product-cart-total']//span[@class='price']";
	public static final String MOBILE_LINK = "//a[text()='Mobile']";	
	public static final String ADD_PRODUCT_MESSAGE_TEXT = "//li[@class='success-msg']//span";	
	public static final String PRODUCT_NAME_TEXT_LIST = "//h2[@class='product-name']/a";	
	public static final String PRODUCT_QUANTITY_TEXT_LIST = "//td[@class='product-cart-actions']/input";	
	public static final String PRODUCT_PRICE_TEXT_LIST = "//td[@class='product-cart-price']//span[@class='price']";	
	public static final String PRODUCT_TOTALPRICE_TEXT_LIST = "//td[@class='product-cart-total']//span[@class='price']";	
	public static final String DISCOUNT_CODES_TEXT_BOX = "//input[@id='coupon_code']";	
	public static final String DISCOUNT_CODES_APPLY_BUTTON = "//span[text()='Apply']";	
}
