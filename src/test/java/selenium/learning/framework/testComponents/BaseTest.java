package selenium.learning.framework.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import selenium.learning.framework.pageObjects.LoginPage;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {
	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() {
		Properties prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/globalProperties.properties");
			prop.load(fis);
			// String browserName = prop.getProperty("browser");
			String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
					: prop.getProperty("browser");
			if (browserName.contains("chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.setAcceptInsecureCerts(true);
				if (browserName.contains("headless"))
					options.addArguments("headless");
				driver = new ChromeDriver(options);
				
			} else if (browserName.contains("edge")) {
				EdgeOptions options = new EdgeOptions();
				options.setAcceptInsecureCerts(true);
				if (browserName.contains("headless"))
					options.addArguments("headless");
				driver = new EdgeDriver(options);
			} else if (browserName.contains("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				options.setAcceptInsecureCerts(true);
				if (browserName.contains("headless"))
					options.addArguments("headless");
				driver = new FirefoxDriver();
			}
			//driver.manage().window().setSize(new Dimension(1440,900));
			//driver.manage().window().fullscreen();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) {
		String jsonContent;
		List<HashMap<String, String>> data = null;
		try {
			jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + filePath));
			ObjectMapper mapper = new ObjectMapper();
			data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file,
					new File(System.getProperty("user.dir") + "/screenshots/" + testCaseName + ".png"));
			return System.getProperty("user.dir") + "/screenshots/" + testCaseName + ".png";
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
