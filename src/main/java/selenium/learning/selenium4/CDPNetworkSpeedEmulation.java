package selenium.learning.selenium4;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v141.network.Network;
import org.openqa.selenium.devtools.v141.network.model.ConnectionType;

public class CDPNetworkSpeedEmulation {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty()));
		devTools.send(Network.emulateNetworkConditions(false, 3000, 20000, 100000, Optional.of(ConnectionType.ETHERNET),
				Optional.empty(), Optional.empty(), Optional.empty()));
		devTools.addListener(Network.loadingFailed(), property -> {
			System.out.println(property.getErrorText());
			System.out.println(property.getTimestamp());
		});
		long startTime = System.currentTimeMillis();
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
		driver.quit();
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

	}

}
