package pageUI;

public class CheckOutPageUI {
	
	public static final String SELECT_ADDRESS_DROPBOX ="//select[@name='billing_address_id']";
	public static final String ADDRESS_TEXT ="//input[@id='billing:street1']";
	public static final String COUNTRY_DROPBOX ="//select[@id='billing:country_id']";
	public static final String STATE_DROPBOX ="//select[@id='billing:region_id']";
	public static final String CITY_TEXT ="//input[@id='billing:city']";
	public static final String ZIP_TEXT ="//input[@id='billing:postcode']";
	public static final String TELEPHONE_TEXT ="//input[@id='billing:telephone']";
	public static final String SHIP_TO_THIS_ADDRESS_RADIO ="//input[@id='billing:use_for_shipping_yes']";
	public static final String BILLING_CONTINUE_BUTTON ="//button[@onclick='billing.save()']";
	public static final String FLAT_RATE_TEXT ="//label[@for='s_method_flatrate_flatrate']/span";
	public static final String SHIPPING_METHOD_CONTINUE_BUTTON ="//button[@onclick='shippingMethod.save()']";
	
	public static final String CHECK_MONEY_ORDER_RADIO ="//input[@id='p_method_checkmo']";
	public static final String PAYMENT_SAVE_CONTINUE_BUTTON = "//button[@onclick='payment.save()']";
	
	public static final String GRAND_TOTAL_TEXT="//tr[@class='last']//span[@class='price']";
	public static final String PLACE_ORDER_BUTTON ="//button[@class='button btn-checkout']";
	
	public static final String ORDER_SUCCEED_MESSAGE_TEXT ="//div[@class='page-title']/h1";
	
}
