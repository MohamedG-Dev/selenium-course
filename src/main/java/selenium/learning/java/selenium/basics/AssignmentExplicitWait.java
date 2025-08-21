package selenium.learning.java.selenium.basics;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssignmentExplicitWait {

	public static void main(String[] args) throws InterruptedException, AWTException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
		driver.findElement(By.id("password")).sendKeys("learning");
		driver.findElement(By.xpath("//span[contains(text(),'User')]/following-sibling::input")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.modal-content")));
		driver.findElement(By.cssSelector("#okayBtn")).click();
		Select select = new Select(driver.findElement(By.cssSelector("select.form-control")));
		select.selectByVisibleText("Consultant");
		driver.findElement(By.id("terms")).click();
		driver.findElement(By.id("signInBtn")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Shop Name']")));
		List<WebElement> addButtons = driver.findElements(By.xpath("//button[contains(text(),'Add')]"));
		for (WebElement addButton : addButtons)
			addButton.click();
		driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[text()='Quantity']")));
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		driver.findElement(By.id("country")).sendKeys("India");
	}

}
