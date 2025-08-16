package selenium.learning.java.selenium.basics;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddItemToCart {

	public static void main(String[] args) {
		int count = 0;
		String[] productList = { "Beans", "Cucumber", "Brocolli", "Orange", "Tomato" };
		List<String> listOFProducts = Arrays.asList(productList);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		List<WebElement> productNames = driver.findElements(By.cssSelector("h4.product-name"));
		for (int i = 0; i < productNames.size(); i++) {
			String[] productname = productNames.get(i).getText().split("-");
			String formattedName = productname[0].trim();
			if (listOFProducts.contains(formattedName)) {
				count++;
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if (count == productList.length)
					break;
			}
		}

	}

}
