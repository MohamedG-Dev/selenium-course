package selenium.learning.windowAutoIT;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadingFile {

	public static void main(String[] args) throws InterruptedException, IOException {
		String downloadPath = System.getProperty("user.dir")+"/chromeDownlads";
		ChromeOptions options = new ChromeOptions();
		HashMap<String,Object> prefs = new HashMap<String,Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", prefs);
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.ilovepdf.com/word_to_pdf");
		driver.findElement(By.id("pickfiles")).click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\autoITExeFile\\testfile.exe");
		// Download File
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("processTaskTextBtn"))))
				.click();
		Thread.sleep(80000);
		driver.quit();
	}

}
