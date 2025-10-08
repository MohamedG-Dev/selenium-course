package selenium.learning.java.selenium.miscellaneous;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class BrokenLinksExample {

	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		// String url =
		// driver.findElement(By.xpath("//a[contains(@href,'soapui')]")).getAttribute("href");
		// check all broken links
		SoftAssert softAssert = new SoftAssert();
		List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
			int responseCode = connection.getResponseCode();
			// System.out.println(responseCode);
			softAssert.assertTrue(responseCode < 400,
					"The Broken link is: " + link.getText() + " with the status Code: " + responseCode);
		}
		driver.quit();
		softAssert.assertAll();
	}

}
