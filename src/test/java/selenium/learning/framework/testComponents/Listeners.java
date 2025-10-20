package selenium.learning.framework.testComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import selenium.learning.framework.utilities.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extentReports = ExtentReporterNG.getReportObject();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();//Thread Safe

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getMethod().getMethodName());
		threadLocal.set(extentTest);//assigns unique thread id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		threadLocal.get().log(Status.PASS, result.getMethod().getMethodName() + " is PASSED!!!!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		threadLocal.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		threadLocal.get().addScreenCaptureFromPath(getScreenShot(result.getMethod().getMethodName(), driver),
				result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

}
