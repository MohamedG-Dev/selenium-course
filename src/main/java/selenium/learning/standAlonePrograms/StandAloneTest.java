package selenium.learning.standAlonePrograms;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		String productName = "ZARA COAT 3";
		String countryName = "India";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("thomasshelby@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Thomas@123");
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.xpath("//h5/b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean productMatch = cartProducts.stream().anyMatch(product -> product.getText().equals(productName));
		Assert.assertTrue(productMatch);
		WebElement checkoutButton = driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"));
		Actions actions = new Actions(driver);
		actions.scrollToElement(checkoutButton).click(checkoutButton).build().perform();
		actions.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']"))).sendKeys(countryName).build().perform();
		WebElement countryResults = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		countryResults.findElements(By.cssSelector(".ta-item span")).stream()
				.filter(country -> country.getText().equals(countryName)).findFirst().orElse(null).click();
		WebElement proceedButton = driver.findElement(By.cssSelector(".action__submit"));
		actions.click(proceedButton).build().perform();
		String message = driver.findElement(By.cssSelector(".hero-primary")).getText().trim();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
	}

}
