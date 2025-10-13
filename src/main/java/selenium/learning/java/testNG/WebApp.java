package selenium.learning.java.testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WebApp {

	@Test(groups = { "smoke" })
	public void weblogin() {
		System.out.println("web login successfull");
	}

	@Parameters({ "url" })
	@Test(groups = { "regression" })
	public void weblogin1(String url) {
		System.out.println("web login1 successfull");
		System.out.println("web login url: " + url);
	}

	@Test(groups = { "regression" })
	public void weblogin2() {
		System.out.println("web login2 successfull");
	}

	@Test(groups = { "regression" })
	public void weblogin3() {
		System.out.println("web login3 successfull");
	}

	@Test(groups = { "regression" })
	public void weblogin4() {
		System.out.println("web login4 successfull");
	}

	@Test(groups = { "regression", "smoke" })
	public void webacess() {
		System.out.println("web access");
	}

	@Test(groups = { "smoke" })
	public void weblogout() {
		System.out.println("web logout");
	}

	@Parameters({ "url", "name" })
	@Test(groups = { "smoke", "regression" })
	public void demo(String url, String name) {
		System.out.println("web demo");
		System.out.println("Web demo url: " + url);
		System.out.println("web demo name attribute: " + name);
	}

	@Test(dataProvider="dataProviderdemo1")
	public void testWebLogin(String userName,String password) {
		System.out.println("Web Login username: "+userName);
		System.out.println("Web Login password: "+password);
	}

	@DataProvider(name = "dataProviderdemo1")
	public Object[][] getData() {
		Object[][] data = new Object[3][2];
		data[0][0]="username->Rohit";
		data[0][1]="password->rh123";
		data[1][0]="username->Virat";
		data[1][1]="password->vk234";
		data[2][0]="username->MSD";
		data[2][1]="passwrd->msd234";
		return data;
	}
}
