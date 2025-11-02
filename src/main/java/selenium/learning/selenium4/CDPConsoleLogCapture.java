package selenium.learning.selenium4;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class CDPConsoleLogCapture {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// Listeners onTestFailure()
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.linkText("Browse Products")).click();
		Thread.sleep(2000);
		driver.findElement(By.partialLinkText("Selenium")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".add-to-cart")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Cart")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("exampleInputEmail1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");
		Thread.sleep(2000);
		LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
		List<LogEntry> allLogs = logs.getAll();
		allLogs.forEach(log -> System.out.println(log.getMessage()));
		driver.quit();

	}

}
