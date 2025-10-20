package selenium.learning.framework.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.learning.framework.pageObjects.CartPage;
import selenium.learning.framework.pageObjects.CheckoutPage;
import selenium.learning.framework.pageObjects.ConfirmationPage;
import selenium.learning.framework.pageObjects.OrdersPage;
import selenium.learning.framework.pageObjects.ProductCatalog;
import selenium.learning.framework.testComponents.BaseTest;
import selenium.learning.framework.testComponents.RetryFailureTests;

public class SubmitOrderTest extends BaseTest {
	private String productName = "ZARA COAT 3";
	private String countryName = "India";

	@Test(dataProvider = "testData", groups = { "purchase" }, retryAnalyzer = RetryFailureTests.class)
	public void submitOrder(Map<String, String> map) {
		ProductCatalog catalog = loginPage.loginApplication(map.get("email"), map.get("password"));

		catalog.addProductToCart(map.get("product"));
		CartPage cartPage = catalog.goToCartPage();

		boolean productMatch = cartPage.verifyProductName(map.get("product"));
		Assert.assertTrue(productMatch);
		CheckoutPage checkoutPage = cartPage.gotToCheckout();

		checkoutPage.selectCountry(map.get("country"));
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String message = confirmationPage.getConfirmationText();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalog catalog = loginPage.loginApplication("thomasshelby@gmail.com", "Thomas@123");
		OrdersPage ordersPage = catalog.goToOrdersPage();
		ordersPage.verifyOrderedProductDislay(productName);
	}

	@DataProvider(name = "testData")
	public Object[][] getData() {
		// 1st way of creating and sending the data
//		return new Object[][] {
//				 { "thomasshelby@gmail.com", "Thomas@123", "ZARA COAT 3", "India" },
//				{ "adashelby@gmail.com", "Adashelby@123", "ADIDAS ORIGINAL", "India" } 
//				 };

		// 2nd way of creating and sendig data using maps
//		Map<String,String> map = new HashMap<>();
//		map.put("email", "thomasshelby@gmail.com");
//		map.put("password", "Thomas@123");
//		map.put("product", "ZARA COAT 3");
//		map.put("country", "India");
//		
//		Map<String,String> map1= new HashMap<>();
//		map1.put("email", "adashelby@gmail.com");
//		map1.put("password", "Adashelby@123");
//		map1.put("product", "ADIDAS ORIGINAL");
//		map1.put("country", "India");

		// 3rd way: using JSON File for fetching the data and using it in TestCase
		List<HashMap<String, String>> data = getJsonDataToMap("/src/test/resources/purchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
