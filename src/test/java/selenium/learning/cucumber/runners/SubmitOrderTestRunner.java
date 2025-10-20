package selenium.learning.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = "selenium.learning.cucumber.stepDefinitions", monochrome = true, plugin = {
		"html:target/cucumberReports/cucumber.html" }, dryRun = false
				//, tags = "@Regression"
				)
public class SubmitOrderTestRunner extends AbstractTestNGCucumberTests {

}
