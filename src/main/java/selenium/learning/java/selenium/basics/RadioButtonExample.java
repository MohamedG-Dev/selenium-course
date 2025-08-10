package selenium.learning.java.selenium.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class RadioButtonExample {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		//System.out.println(driver.findElement(By.name("ctl00$mainContent$view_date2")).isEnabled());
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		//System.out.println(driver.findElement(By.name("ctl00$mainContent$view_date2")).isEnabled());
		String styleValue=driver.findElement(By.id("Div1")).getAttribute("Style");
		System.out.println(styleValue);
		if(styleValue.contains("1"))
		{
			System.out.println("The date picker2 is enable2");
			Assert.assertTrue(true);
		}else {
			System.out.println("The date picker2 is not enabled");
			Assert.assertTrue(false);
		}

	}

}
