package selenium.learning.framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.learning.framework.utilities.Utility;

public class LoginPage extends Utility {
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	private WebElement userEmail;

	@FindBy(id = "userPassword")
	private WebElement userPassword;

	@FindBy(id = "login")
	private WebElement loginBtn;
	
	@FindBy(css="div[class*='flyInOut']")
	private WebElement loginErrMessage;

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

	public ProductCatalog loginApplication(String emailID, String password) {
		userEmail.sendKeys(emailID);
		userPassword.sendKeys(password);
		scrollToElementAndClickAction(loginBtn);
		//loginBtn.click();
		return new ProductCatalog(driver);
	}
	
	public String getErrorMessage() {
		waitForElementToBeVisible(loginErrMessage);
		return loginErrMessage.getText().trim();
	}
}
