package selenium.learning.java.selenium.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Assignment6_PracticeAssignment {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		// 1. select option2 checkbox and get the text of it
		driver.findElement(By.xpath("//div[@id='checkbox-example']/descendant::input[@id='checkBoxOption2']")).click();
		String optionSelected = driver.findElement(By.xpath(
				"//div[@id='checkbox-example']/descendant::input[@id='checkBoxOption2']/parent::label"))
				.getText().trim();
		// 2. select the same option 2 from dropdown
		Select select = new Select(driver.findElement(By.id("dropdown-class-example")));
		select.selectByVisibleText(optionSelected);
		// 3. enter option2 into edit box of alert example
		driver.findElement(By.name("enter-name")).sendKeys(optionSelected);
		// 4. click on Alert button and check the alert message has the option2 in it.
		driver.findElement(By.id("alertbtn")).click();
		String alertMsg = driver.switchTo().alert().getText();
		System.out.println(alertMsg);
		Assert.assertTrue(alertMsg.contains(optionSelected));
		driver.quit();

	}

}
