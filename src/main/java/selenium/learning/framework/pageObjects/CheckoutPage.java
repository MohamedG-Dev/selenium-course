package selenium.learning.framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.learning.framework.utilities.Utility;

public class CheckoutPage extends Utility {

	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	private WebElement countryInputField;

	@FindBy(css = ".ta-results")
	private WebElement countryResults;
	
	@FindBy(css=".action__submit")
	private WebElement proceedButton;

	private By countryList = By.cssSelector(".ta-item span");

	public void selectCountry(String countryName) {
		scrollIntoViewAction(proceedButton);
		sendKeysAction(countryInputField, countryName);
		waitForElementToBeVisible(countryResults);
		countryResults.findElements(countryList).stream().filter(country -> country.getText().equals(countryName))
				.findFirst().orElse(null).click();
	}
	
	public ConfirmationPage submitOrder() {
		scrollToElementAndClickAction(proceedButton);
		return new ConfirmationPage(driver);
	}
}
