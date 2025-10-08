package selenium.learning.java.selenium.miscellaneous;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MaximizingWindowAndDeleteCookies {

	public static void main(String[] args) {
		WebDriver driver  = new ChromeDriver();
		driver.manage().window().maximize();
		//Delete all cookies
		driver.manage().deleteAllCookies();
		//Delete a specific Cookie
		//driver.manage().deleteCookieNamed("name of the cookie=>eg: sessionKey");
		driver.get("https://www.google.com/");
		driver.quit();
	}

}
