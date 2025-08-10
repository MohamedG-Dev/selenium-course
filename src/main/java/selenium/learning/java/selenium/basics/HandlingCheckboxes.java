package selenium.learning.java.selenium.basics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class HandlingCheckboxes {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		boolean isselected = driver.findElement(By.xpath("//input[contains(@id,'SeniorCitizenDiscount')]"))
				.isSelected();
		System.out.println(isselected);
		Assert.assertFalse(isselected);
		driver.findElement(By.xpath("//input[contains(@id,'SeniorCitizenDiscount')]")).click();
		isselected = driver.findElement(By.xpath("//input[contains(@id,'SeniorCitizenDiscount')]")).isSelected();
		System.out.println(isselected);
		Assert.assertTrue(isselected);
		System.out.println(driver.findElements(By.cssSelector("input[type='checkbox']")).size());
		Assert.assertEquals(6, driver.findElements(By.cssSelector("input[type='checkbox']")).size());
		driver.quit();
	}

}
