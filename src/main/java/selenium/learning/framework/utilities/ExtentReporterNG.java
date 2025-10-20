package selenium.learning.framework.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		String filePath = System.getProperty("user.dir")+"/src/test/resources/extentReports/extentReports.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
		sparkReporter.config().setReportName("Web Automation Results");
		sparkReporter.config().setDocumentTitle("Test Results");

		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Tester", "Kevin Nair");
		return extentReports;
	}

}
