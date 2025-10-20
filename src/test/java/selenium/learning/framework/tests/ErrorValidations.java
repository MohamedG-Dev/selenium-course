package selenium.learning.framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import selenium.learning.framework.pageObjects.CartPage;
import selenium.learning.framework.pageObjects.CheckoutPage;
import selenium.learning.framework.pageObjects.ConfirmationPage;
import selenium.learning.framework.pageObjects.ProductCatalog;
import selenium.learning.framework.testComponents.BaseTest;
import selenium.learning.framework.testComponents.RetryFailureTests;

public class ErrorValidations extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = RetryFailureTests.class)
	public void loginErrorValidation() {
		loginPage.loginApplication("shelby@gmail.com", "Thomas@123");
		Assert.assertEquals(loginPage.getErrorMessage(), "Incorrect email or password.");
		/*
		 * deliberately failing this test case to see the screenshot in the extent
		 * reports
		 */
		// Assert.assertEquals(loginPage.getErrorMessage(), "INCORRECT EMAIL OR
		// PASSWORD.");
	}

	@Test(groups = { "ErrorHandling" })
	public void submitOrderErrorHandling() {
		String productName = "ZARA COAT 3";

		ProductCatalog catalog = loginPage.loginApplication("adashelby@gmail.com", "Adashelby@123");

		catalog.addProductToCart(productName);
		CartPage cartPage = catalog.goToCartPage();

		boolean productMatch = cartPage.verifyProductName("ZARA COAT 300");
		Assert.assertFalse(productMatch);

	}
}
