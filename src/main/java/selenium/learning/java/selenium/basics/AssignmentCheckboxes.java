package selenium.learning.java.selenium.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AssignmentCheckboxes {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		// 1.Check the first Checkbox and verify if it is successfully checked and
		// Uncheck it again to verify if it is successfully Unchecked
		WebElement checkbox1 = driver.findElement(By.id("checkBoxOption1"));
		checkbox1.click();
		Assert.assertTrue(checkbox1.isSelected());
		checkbox1.click();
		Assert.assertFalse(checkbox1.isSelected());
		// 2.How to get the Count of number of check boxes present in the page
		int checkboxSize = driver
				.findElements(By.xpath("//div[@id='checkbox-example']//child::input[@type='checkbox']")).size();
		System.out.println(checkboxSize);
		driver.close();
	}

}
