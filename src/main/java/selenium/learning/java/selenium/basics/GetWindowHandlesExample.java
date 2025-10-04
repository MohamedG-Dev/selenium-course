package selenium.learning.java.selenium.basics;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetWindowHandlesExample {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		driver.findElement(By.className("blinkingText")).click();

		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		String parentWindow = itr.next();
		String childWindow = itr.next();
		driver.switchTo().window(childWindow);
		String emailPara = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".im-para.red")))).getText();
		System.out.println(emailPara);
		emailPara = emailPara.split("at")[1].trim().split(" ")[0].trim();
		System.out.println(emailPara);
		driver.switchTo().window(parentWindow);
		driver.findElement(By.id("username")).sendKeys(emailPara);
		driver.quit();

	}

}
