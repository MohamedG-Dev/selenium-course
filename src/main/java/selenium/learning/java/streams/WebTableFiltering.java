package selenium.learning.java.streams;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTableFiltering {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("search-field")).sendKeys("rice");
		List<WebElement> products = driver
				.findElements(By.xpath("//table[contains(@class,'table-bordered')]/tbody/tr/td[1]"));
		List<String> filteredList = products.stream().filter(name -> name.getText().contains("Rice"))
				.map(WebElement::getText).collect(Collectors.toList());
		filteredList.forEach(System.out::println);
		
		driver.quit();

	}

}
