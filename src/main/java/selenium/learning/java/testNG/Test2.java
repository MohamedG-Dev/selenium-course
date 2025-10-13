package selenium.learning.java.testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test2 {

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
		System.out.println("Before method annotation is triggered for Test2 Class");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After method annotation is triggered Test2 Class");
	}

	@Test(groups = { "regression" },dependsOnMethods={"test5"})
	public void test3() {
		System.out.println("test3");
	}

	@Test(groups = { "smoke" })
	public void test4() {
		System.out.println("test4");
	}
	
	@Parameters({"url","name"})
	@Test(timeOut=5000)
	public void test5(String url,String name) {
		System.out.println("test5");
		System.out.println("test 5 - URL Name:"+url);
		System.out.println("name attribute: "+name);
	}
	
	@Test(enabled=false)
	public void test6() {
		System.out.println("test6");
	}
	
	@Test(dataProvider="TESTDATA")
	public void dataTest(String name,String pass) {
		System.out.println("USERNAME: "+name+" and PASSWORD: "+pass);
	}
	
	@DataProvider(name="TESTDATA")
	public Object[][] testData() {
		return new Object[][] {
			{"user1","pass1"},
			{"user2","pass2"},
			{"user3","pass3"}
		};
	}
	
	

}
