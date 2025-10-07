package selenium.learning.java.selenium.basics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment7_WebTables {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		int rowsize = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr")).size();
		System.out.println("Total Number of Rows are: "+rowsize);
		List<WebElement> columnHeaders = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr[1]/th"));
		System.out.println("Column size is:"+columnHeaders.size());
		//printing 2nd row data
		Map<String,String> map = new HashMap<>();
		List<WebElement> row2Data = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr[3]/td"));
		for(int i=0;i<columnHeaders.size();i++) {
			map.put(columnHeaders.get(i).getText(), row2Data.get(i).getText());
		}
		System.out.println(map);
		driver.quit();
	}

}
