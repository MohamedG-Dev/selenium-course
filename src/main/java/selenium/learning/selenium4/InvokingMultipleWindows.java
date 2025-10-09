package selenium.learning.selenium4;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class InvokingMultipleWindows {

	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		//driver.switchTo().newWindow(WindowType.TAB);
		driver.switchTo().newWindow(WindowType.WINDOW);
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> windows = windowHandles.iterator();
		String parentWindow = windows.next();
		String childWindow = windows.next();
		driver.switchTo().window(childWindow);
		driver.get("https://rahulshettyacademy.com/");
		String courseName = driver.findElement(By.xpath("(//a[contains(@href,'https://courses.rahulshettyacademy.com/p')])[2]")).getText();
		driver.switchTo().window(parentWindow);
		WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
		name.sendKeys(courseName);
		File file =name.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/screenshot/elementScreenshot.png"));
		file = driver.findElement(By.xpath("//div[@class='jumbotron']")).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/screenshot/protractor.png"));
		
		System.out.println(name.getRect().getDimension().getHeight());
		System.out.println(name.getRect().getDimension().getWidth());
		driver.quit();
	}

}
