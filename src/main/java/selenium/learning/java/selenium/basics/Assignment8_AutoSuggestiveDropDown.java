package selenium.learning.java.selenium.basics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Assignment8_AutoSuggestiveDropDown {

	public static void main(String[] args) throws InterruptedException {
		String countryName = "United Arab Emirates";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.id("autocomplete")).sendKeys("uni");
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.ARROW_DOWN).build().perform();
		List<WebElement> country = driver.findElements(By.xpath("//li[@class='ui-menu-item']/div"));
		System.out.println("size of countries: "+country.size());
		for (WebElement state : country) {
			if (state.getText().equals(countryName))
				state.click();
		}
		Thread.sleep(3000);
		actions.keyDown(Keys.TAB).keyUp(Keys.TAB).build().perform();
		String value = driver.findElement(By.id("autocomplete")).getAttribute("value");
		System.out.println("value=======" + value);
		driver.quit();

	}

}
