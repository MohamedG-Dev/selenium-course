package selenium.learning.selenium4;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class CDPSetGeoLocations {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		DevTools devTools=driver.getDevTools();
		devTools.createSession();
		HashMap<String,Object> coordinates = new HashMap<>();
		coordinates.put("latitute", 35);
		coordinates.put("longitude",6);
		coordinates.put("accuracy",1);
		driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
		driver.get("http://www.google.com");
		driver.findElement(By.name("q")).sendKeys("netflix",Keys.ENTER);
		Thread.sleep(3000);
		driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
		Thread.sleep(3000);
		String title = driver.findElement(By.tagName("h1")).getText();
		System.out.println(title);

	}

}
