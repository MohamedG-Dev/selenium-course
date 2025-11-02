package selenium.learning.selenium4;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v141.network.Network;

import com.google.common.collect.ImmutableList;


public class CDPBlockUnwatedNetworkURLs {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		 DevTools devTools=driver.getDevTools();
		 devTools.createSession();
		 devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		 devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg","*.css")));
		 long startTime = System.currentTimeMillis();
		 driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		 driver.findElement(By.cssSelector("a[routerlink*='products']")).click();
		 Thread.sleep(3000);
		 driver.findElement(By.linkText("Selenium")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.cssSelector(".add-to-cart")).click();
		 Thread.sleep(2000);
		 System.out.println(driver.findElement(By.tagName("p")).getText());
		 driver.quit();
		 long endTime = System.currentTimeMillis();
		 System.out.println("Difference Time: "+(endTime-startTime));

	}

}
