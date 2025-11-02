package selenium.learning.selenium4;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class CDPCommandsTest {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();
		params.put("width",600);
		params.put("height", 1000);
		params.put("deviceScaleFactor", 50);
		params.put("mobile", true);
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", params);
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.className("navbar-toggler")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Library")).click();
	}

}
