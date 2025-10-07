package selenium.learning.java.selenium.basics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class JavaScriptExecutorExample {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");
		Thread.sleep(4000);
		jse.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");

		List<WebElement> amountList = driver
				.findElements(By.xpath("//div[@class='tableFixHead']/table/tbody/tr/td[4]"));
		// amountList.stream().map(WebElement::getText).filter(txt ->
		// !txt.isEmpty()).mapToInt(Integer::parseInt).sum();
		int sum = amountList.stream().map(WebElement::getText).mapToInt(value -> {
			int num = Integer.parseInt(value);
			System.out.println("Parsed value: " + num);
			return num;
		}).sum();
		System.out.println("Total amount value: " + sum);
		String collectedAmount = driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim();
		System.out.println(collectedAmount);
		Assert.assertEquals(sum, Integer.parseInt(collectedAmount));
		driver.quit();

	}

}
