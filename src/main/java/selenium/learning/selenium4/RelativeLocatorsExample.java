package selenium.learning.selenium4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocatorsExample {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		WebElement username = driver.findElement(By.cssSelector("input[name='name']"));
		String text = driver.findElement(with(By.tagName("label")).above(username)).getText();
		System.out.println(text);
		driver.findElement(with(By.tagName("input")).below(By.xpath("//label[@for='dateofBirth']"))).click();
		driver.findElement(with(By.tagName("input")).toLeftOf(By.xpath("//label[text()='Check me out if you Love IceCreams!']"))).click();
		String labelText = driver.findElement(with(By.tagName("label")).toRightOf(By.id("inlineRadio1"))).getText();
		System.out.println(labelText);
		//driver.quit();

	}

}
