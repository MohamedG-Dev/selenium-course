package selenium.learning.cucumber.stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.learning.framework.pageObjects.CartPage;
import selenium.learning.framework.pageObjects.CheckoutPage;
import selenium.learning.framework.pageObjects.ConfirmationPage;
import selenium.learning.framework.pageObjects.LoginPage;
import selenium.learning.framework.pageObjects.ProductCatalog;
import selenium.learning.framework.testComponents.BaseTest;

public class SubmitOrderTestStepDefinition extends BaseTest {
	public LoginPage login;
	public ProductCatalog catalog;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;

	@Given("user navigates to application")
	public void user_navigates_to_application() {
		login = launchApplication();
	}

	@Given("user logs in to application {string} and {string}")
	public void user_logs_in_to_application(String email, String password) {
		catalog = loginPage.loginApplication(email, password);
	}

	@When("user adds the product {string} to cart")
	public void user_adds_the_product_to_cart(String productName) {
		catalog.addProductToCart(productName);
		cartPage = catalog.goToCartPage();
	}

	@Then("user checksout product name {string} and selects {string} and submit Order")
	public void user_checksout_product_name_and_submit_order(String productName, String countryName) {
		boolean productMatch = cartPage.verifyProductName(productName);
		Assert.assertTrue(productMatch);
		checkoutPage = cartPage.gotToCheckout();

		checkoutPage.selectCountry(countryName);
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("user verifies {string} message in the Confirmation page")
	public void user_verifies_message_in_the_confirmation_page(String string) {
		String message = confirmationPage.getConfirmationText();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
	}

	@Then("check for error message {string}")
	public void check_for_error_message(String string) {
		Assert.assertEquals(loginPage.getErrorMessage(), "Incorrect email or password.");
		driver.quit();
	}

}
