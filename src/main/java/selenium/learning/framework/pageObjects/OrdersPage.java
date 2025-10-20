package selenium.learning.framework.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.learning.framework.utilities.Utility;

public class OrdersPage extends Utility {
	private WebDriver driver;

	@FindBy(xpath = "//table/tbody/tr/td[2]")
	private List<WebElement> productNames;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyOrderedProductDislay(String productName) {
		return productNames.stream().anyMatch(name -> name.getText().equals(productName));

	}

}
