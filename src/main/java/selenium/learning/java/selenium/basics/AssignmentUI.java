package selenium.learning.java.selenium.basics;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AssignmentUI {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.name("name")).sendKeys("Thomas Shelby");
		driver.findElement(By.name("email")).sendKeys("thomasshelby@shelby.com");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("shelby");
		driver.findElement(By.id("exampleCheck1")).click();
		WebElement genderDropdown = driver.findElement(By.id("exampleFormControlSelect1"));
		Select select = new Select(genderDropdown);
		select.selectByVisibleText("Female");
		driver.findElement(By.xpath("//label[text()='Student']/preceding-sibling::input")).click();
		driver.findElement(By.name("bday")).sendKeys("01/22/1987");
		driver.findElement(By.xpath("//input[contains(@class,'btn-success')]")).click();
		String successMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-success')]")).getText();
		System.out.println(successMessage);
		Assert.assertTrue(successMessage.contains("Success!"));
		driver.quit();
	}

}
