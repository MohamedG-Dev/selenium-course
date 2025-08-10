package selenium.learning.java.selenium.basics;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DynamicDropdowns {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		WebElement source = driver.findElement(By.cssSelector("select[id='ctl00_mainContent_ddl_originStation1']"));
		source.click();
		//Select select = new Select(source);
		//select.selectByValue("BLR");

	}

}
