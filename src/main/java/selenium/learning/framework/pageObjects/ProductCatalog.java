package selenium.learning.framework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.learning.framework.utilities.Utility;

public class ProductCatalog extends Utility {
	private WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	private List<WebElement> products;

	private By productList = By.cssSelector(".mb-3");
	private By addToCartButton = By.cssSelector(".card-body button:last-of-type");
	private By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProducts() {
		waitForElementToBeVisible(productList);
		return products;
	}

	public WebElement getProductByName(String productName) {
		return getProducts().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().trim().equals(productName)).findFirst()
				.orElse(null);
	}

	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(addToCartButton).click();
		waitForElementToBeVisible(toastMessage);
		waitForElementToDisappear(toastMessage);
	}

}
