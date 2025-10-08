package selenium.learning.java.selenium.miscellaneous;

import java.io.File;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SSLCertificationCheck {

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://expired.badssl.com/");
		System.out.println(driver.getTitle());
		driver.quit();
		
		//Add the WebDriver proxy capability.
//		Proxy proxy = new Proxy();
//		proxy.setHttpProxy("myhttpproxy:337");
//		options.setCapability("proxy", proxy);
		
		//Add a ChromeDriver-specific capability.
		options.addExtensions(new File("/path/to/extension.crx"));
		
		ChromeDriver chromeDriver = new ChromeDriver(options);

	}

}
