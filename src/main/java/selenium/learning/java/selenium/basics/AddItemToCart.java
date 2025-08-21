package selenium.learning.java.selenium.basics;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddItemToCart {

	public static void main(String[] args) {

		String[] productList = { "Beans", "Cucumber", "Brocolli", "Orange", "Tomato" };
		List<String> listOFProducts = Arrays.asList(productList);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		addItemstoCart(driver, listOFProducts, productList.length);
		driver.findElement(By.xpath("//a[@class='cart-icon']/img")).click();
		driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector("button.promoBtn")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
		System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
		driver.quit();
	}

	public static void addItemstoCart(WebDriver driver, List<String> listOFProducts, int productListSize) {
		int count = 0;
		List<WebElement> productNames = driver.findElements(By.cssSelector("h4.product-name"));
		for (int i = 0; i < productNames.size(); i++) {
			String[] productname = productNames.get(i).getText().split("-");
			String formattedName = productname[0].trim();
			if (listOFProducts.contains(formattedName)) {
				count++;
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if (count == productListSize)
					break;
			}
		}
	}

}
