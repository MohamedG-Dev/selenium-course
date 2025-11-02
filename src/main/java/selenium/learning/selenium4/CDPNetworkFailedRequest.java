package selenium.learning.selenium4;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v141.fetch.Fetch;
import org.openqa.selenium.devtools.v141.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v141.fetch.model.RequestStage;
import org.openqa.selenium.devtools.v141.network.model.ErrorReason;
import org.openqa.selenium.devtools.v141.network.model.ResourceType;

public class CDPNetworkFailedRequest {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		Optional<List<RequestPattern>> pattern=Optional.of(List.of(new RequestPattern(Optional.of("*GetBook*"),Optional.<ResourceType>empty(),Optional.<RequestStage>empty())));
		devTools.send(Fetch.enable(pattern, Optional.empty()));
		devTools.addListener(Fetch.requestPaused(), request ->{
			devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
		});

		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
	}

}
