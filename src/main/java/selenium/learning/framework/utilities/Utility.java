package selenium.learning.framework.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.learning.framework.pageObjects.CartPage;
import selenium.learning.framework.pageObjects.OrdersPage;

public class Utility {
	private WebDriver driver;
	private Actions actions;

	public Utility(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
	}

	@FindBy(css = "button[routerlink*='cart']")
	private WebElement cartIcon;

	@FindBy(css = "button[routerlink*='myorders']")
	private WebElement ordersIcon;

	public void waitForElementToBeVisible(By locator) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementToBeVisible(WebElement element) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToDisappear(By locator) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForElementToClickableAndClick(WebElement element) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void scrollToElementAndClickAction(WebElement element) {
		actions.scrollToElement(element).perform();
		waitForElementToClickableAndClick(element);
	}

	public CartPage goToCartPage() {
		cartIcon.click();
		return new CartPage(driver);
	}

	public void sendKeysAction(WebElement element, String value) {
		actions.sendKeys(element, value).build().perform();
	}

	public OrdersPage goToOrdersPage() {
		ordersIcon.click();
		return new OrdersPage(driver);
	}

	public void scrollIntoViewAction(WebElement element) {
		actions = new Actions(driver);
		actions.scrollToElement(element).perform();
	}

}
