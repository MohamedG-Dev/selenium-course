package selenium.learning.java.testNG;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MobileApp {
	@Parameters({"url"})
	@Test(groups = { "smoke" })
	public void mobilelogin(String url) {
		System.out.println("mobile login successfull");
		System.out.println("mobile login url: "+url);
	}

	@Test(groups = { "regression", "smoke" })
	public void mobileacess() {
		System.out.println("mobile access");
	}

	@Test(groups = { "regression" })
	public void mobilelogout() {
		System.out.println("mobile logout");
	}

	@Parameters({"name"})
	@Test(groups = { "smoke" })
	public void mobiletest(String name) {
		System.out.println("mobile test");
		System.out.println("mobile test name: "+name);
	}
}
