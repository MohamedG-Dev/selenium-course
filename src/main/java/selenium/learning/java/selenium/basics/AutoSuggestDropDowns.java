package selenium.learning.java.selenium.basics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AutoSuggestDropDowns {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.findElement(By.id("autosuggest")).sendKeys("ind");
		Thread.sleep(3000);
		List<WebElement> menuItems = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
		for (WebElement menuItem : menuItems) {
			if (menuItem.getText().trim().equals("India")) {
				menuItem.click();
				break;
			}

		}
		String value = driver.findElement(By.id("autosuggest")).getAttribute("value");
		System.out.println(value);
	}

}
