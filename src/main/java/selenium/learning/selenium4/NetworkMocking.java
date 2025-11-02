package selenium.learning.selenium4;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v141.fetch.Fetch;
import org.openqa.selenium.devtools.v141.network.model.Request;

public class NetworkMocking {
	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
		devTools.addListener(Fetch.requestPaused(), request -> {
			Request req = request.getRequest();
			if (req.getUrl().contains("shetty")) {
				String mockURL = req.getUrl().replace("=shetty", "=BadGuy");
				System.out.println(mockURL);
				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockURL),
						Optional.of(req.getMethod()), Optional.empty(), Optional.empty(), Optional.empty()));
			} else {
				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(req.getUrl()),
						Optional.of(req.getMethod()), Optional.empty(), Optional.empty(), Optional.empty()));
			}
		});
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.tagName("p")).getText());
		driver.quit();
	}
}
