package selenium.learning.framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.learning.framework.utilities.Utility;

public class ConfirmationPage extends Utility {
	private WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	private WebElement confirmationText;

	public String getConfirmationText() {
		return confirmationText.getText().trim();
	}
}
