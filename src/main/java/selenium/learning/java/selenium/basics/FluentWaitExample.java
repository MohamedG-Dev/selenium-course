package selenium.learning.java.selenium.basics;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class FluentWaitExample {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		driver.findElement(By.xpath("//div[@id='start']/button[text()='Start']")).click();
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		WebElement resultSuccess = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				if (driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed())
					return driver.findElement(By.xpath("//h4[text()='Hello World!']"));
				else
					return null;
			}
		});
		System.out.println(resultSuccess.isDisplayed());
		System.out.println(resultSuccess.getText());
		driver.quit();
	}

}
