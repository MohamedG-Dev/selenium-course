package selenium.learning.framework.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.learning.framework.utilities.Utility;

public class CartPage extends Utility {
	private WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	private WebElement checkoutButton;

	public boolean verifyProductName(String productName) {
		return cartProducts.stream().anyMatch(product -> product.getText().equals(productName));
	}

	public CheckoutPage gotToCheckout() {
		scrollToElementAndClickAction(checkoutButton);
		return new CheckoutPage(driver);
	}

}
