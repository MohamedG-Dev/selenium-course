package selenium.learning.java.selenium.miscellaneous;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsExample {
	ExtentReports report;
	@BeforeTest
	public void extentReportsConfig() {
		String filePath = System.getProperty("user.dir")+"/src/main/resources/extentReports/index.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(new File(filePath));
		sparkReporter.config().setReportName("WebAutomationResults");
		sparkReporter.config().setDocumentTitle("Automation Test Results");
		
		report = new ExtentReports();
		report.attachReporter(sparkReporter);
		report.setSystemInfo("Tester", "Karun Nair");
	}

	@Test
	public void demo() throws IOException {
		ExtentTest extentTest = report.createTest("Launching Application");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		String title = driver.getTitle();
		System.out.println(title);
		
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/screenshots/failed.png"));
		extentTest.addScreenCaptureFromPath(System.getProperty("user.dir")+"/screenshots/failed.png");
		extentTest.fail("result doesn't match");
		driver.quit();
		report.flush();
	}
	
}
