package selenium.learning.java.streams;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class WebTablesSorting {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//th[contains(@aria-label,'Veg/fruit name')]")).click();
		List<WebElement> productList = driver
				.findElements(By.xpath("//table[contains(@class,'table-bordered')]/tbody/tr/td[1]"));
		List<String> list = productList.stream().map(WebElement::getText).collect(Collectors.toList());
		List<String> sortedList = list.stream().sorted().collect(Collectors.toList());
		Assert.assertEquals(sortedList, list);
		// scan the name column with getText -> "Beans" and fetch the price of the
		// veggie
		List<String> price = productList.stream().filter(name -> name.getText().contains("Beans"))
				.map(element -> getVeggiePrice(element)).collect(Collectors.toList());
		price.forEach(System.out::println);
		
		//search for rice using pagination and get the price of it
		do {
			productList = driver
					.findElements(By.xpath("//table[contains(@class,'table-bordered')]/tbody/tr/td[1]"));
		price = productList.stream().filter(name -> name.getText().contains("Rice")).map(element -> getVeggiePrice(element)).collect(Collectors.toList());
		price.forEach(System.out::println);
		if(price.size()==0)
			driver.findElement(By.xpath("//a[@aria-label='Next']")).click();
		}while(price.size()==0);
		driver.quit();
	}

	private static String getVeggiePrice(WebElement name) {

		return name.findElement(By.xpath("following-sibling::td[1]")).getText();
	}

}
