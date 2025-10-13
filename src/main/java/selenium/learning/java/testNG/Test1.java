package selenium.learning.java.testNG;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test1 {

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite annotation is triggered");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite annotation is triggered");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Before test annotation is triggered");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("After test annotation is triggered");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("BeforeClass Annotation is triggered for class Test1");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("AfterClass Annotation is triggered for class Test1");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before method annotation is triggered for Test1 Class");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After method annotation is triggered Test1 Class");
	}

	@Test(groups = { "smoke" })
	public void test1() {
		System.out.println("Test1");
	}

	@Parameters({"url","name"})
	@Test(groups = { "regression" })
	public void test2(String urlName,String name) {
		System.out.println("Test2");
		System.out.println("URL value /is: "+urlName);
		System.out.println("name attribute: "+name);
	}
	
	
	@Test
	public void failTest() {
		Assert.assertTrue(false);
	}

}
