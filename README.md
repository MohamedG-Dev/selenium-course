#Selenium Concepts and Code examples

#Go through the below file for learning path and selenium programs in detail
	File Name: LearningFlowPath
	
## Command to set the path for the browser drivers:
	-> Chrome: System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
	-> Firefox: System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
	-> Edge: System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\drivers\\msedgedriver.exe");
	
## Command to launch Chrome browser: 
	package: 	import org.openqa.selenium.WebDriver;
				import org.openqa.selenium.chrome.ChromeDriver;
	Code:	WebDriver driver = new ChromeDriver();
	
## Command to launch Firefox browser: 
	package: 	import org.openqa.selenium.WebDriver;
				import org.openqa.selenium.firefox.FirefoxDriver;
	Code:	WebDriver driver = new EdgeDriver();
	
## Command to launch Edge browser:
	package: 	import org.openqa.selenium.WebDriver;
				import org.openqa.selenium.edge.EdgeDriver;
	Code:	WebDriver driver = new EdgeDriver();

## Command to maximize browser window:
	Code:	driver.manage().window().maximize();
	
## Command to hit a URL:
	Code: driver.get("https://www.google.com/");
	
## Command to get title of page:
	Code: driver.getTitle();
	
## Command to get current URL:
	Code: driver.getCurrentUrl();
	
## Command to close the browser:
	--> Current window: driver.close();
	--> All open tabs/windows: driver.quit();

## Command to sendKeys:
	Code: driver.findElement(By.id("inputUsername")).sendKeys("rahul");

## Command to click:
	Code: driver.findElement(By.linkText("Forgot your password?")).click();
	
## Command to Clear Text field:
	Code: driver.findElement(By.xpath("//input[@type='text'][2]")).clear();
	
## Finding elements using Locators:
	package: import org.openqa.selenium.By;
	Code:	
		1. id: driver.findElement(By.id("inputUsername")).sendKeys("rahul");
		2. name: driver.findElement(By.name("inputPassword")).sendKeys("hello123");
		3. className: driver.findElement(By.className("signInBtn")).click();
		4. cssSelector: driver.findElement(By.cssSelector("p.error")).getText();
		5. linkText: driver.findElement(By.linkText("Forgot your password?")).click();
		6. xpath: driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Shelby");
		7. tagName: diver.findElement(By.tagName("a")).click();

## Command to navigate to a URL:
	Code: driver.navigate().to("https://www.examples/AutomationPractice/");
	
## Command to navigate back:
	Code: driver.navigate().back();
	
## Command to navigate forward:
	Code: driver.navigate().forward();
	
## Command to refresh:
	Code: driver.navigate().refresh();
	
## Code for Handling static dropdowns:
	Package: import org.openqa.selenium.support.ui.Select;
	Code: WebElement currenyDropDown = driver.findElement(By.xpath("//select[contains(@id,'DropDownListCurrency')]"));
		Select dropdown = new Select(currenyDropDown);
		dropdown.selectByIndex(3);
		System.out.println(dropdown.getFirstSelectedOption().getText());
		dropdown.selectByVisibleText("AED");
		System.out.println(dropdown.getFirstSelectedOption().getText());
		dropdown.selectByValue("INR");
		System.out.println(dropdown.getFirstSelectedOption().getText());	
		
## Command to check a checkbox is selected:
	Code: driver.findElement(By.xpath("//input[contains(@id,'SeniorCitizenDiscount')]")).isSelected();
	
## Command(s) to handle alert:
	Code: driver.switchTo().alert();
		driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		driver.switchTo().alert().dismiss();

## Command for Implicit wait:
	Code: driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	
## Command for Explicit Wait - WebDriverWait
	Package: import org.openqa.selenium.support.ui.WebDriverWait;
	Code: WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
		
## Command for Explicit Wait - FluentWait
	Package: import java.time.Duration;
		import java.util.function.Function;
		import org.openqa.selenium.support.ui.FluentWait;
		import org.openqa.selenium.support.ui.Wait;
	Code: Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		WebElement resultSuccess = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				if (driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed())
					return driver.findElement(By.xpath("//h4[text()='Hello World!']"));
				else
					return null;
			}
		});
		
## Code for Actions Class - moveTo, context click, sendKeys,double click, dragAndDrop
	Package:	import org.openqa.selenium.interactions.Actions;
			 	import org.openqa.selenium.Keys;
	Code:	Actions actions = new Actions(driver);
		WebElement searchBox = driver.findElement(By.cssSelector("input[id='twotabsearchtextbox']"));
		WebElement accountList = driver.findElement(By.xpath("//a[@data-csa-c-slot-id='nav-link-accountList']"));
		//*****send keys and double click***********//
		actions.moveToElement(searchBox).click().keyDown(Keys.SHIFT).sendKeys("iphone").keyUp(Keys.SHIFT).doubleClick()
				.build().perform();
		//************** Moves to specific element and right click(contextClick()) ********************/
		actions.moveToElement(accountList).contextClick().build().perform();
		//******************************* Drag and Drop **************************************************/
		WebElement draggable = driver.findElement(By.id("draggable"));
		WebElement droppable = driver.findElement(By.id("droppable"));
		actions.dragAndDrop(draggable, droppable).build().perform();*
		
## Switching Windows (Child/Parent) Code:
	Package: 	import java.util.Set;
					import java.util.Iterator;
	Code:		Set<String> windows = driver.getWindowHandles();
				Iterator<String> itr = windows.iterator();
				String parentWindow = itr.next();
				String childWindow = itr.next();
				driver.switchTo().window(childWindow);
				driver.switchTo().window(parentWindow);
				
## Frames:
	Code:	name/id => driver.switchTo().frame("frame-top");
			index	=>	driver.switchTo().frame(2);
			WebElement =>	WebElement demoFrame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"))
							driver.switchTo().frame(demoFrame);
## JavascriptExecutor (Scroll example):
	Package: import org.openqa.selenium.JavascriptExecutor;
	Code:	JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");
		jse.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");
		
## ChromeOptions
	Package:	import org.openqa.selenium.chrome.ChromeDriver;
				import org.openqa.selenium.chrome.ChromeOptions;
	Code: 		ChromeOptions options = new ChromeOptions();
				options.setAcceptInsecureCerts(true);
				WebDriver driver = new ChromeDriver(options);
	ChromeOptions options = new ChromeOptions();

	// Add the WebDriver proxy capability.
	Proxy proxy = new Proxy();
	proxy.setHttpProxy("myhttpproxy:3337");
	options.setCapability("proxy", proxy);
	
	// Add a ChromeDriver-specific capability.
	options.addExtensions(new File("/path/to/extension.crx"));
	ChromeDriver driver = new ChromeDriver(options);
	
	//Use a custom profile
	ChromeOptions options = new ChromeOptions();
	options.addArguments("user-data-dir=/path/to/your/custom/profile");
	
	//Start Chrome maximized
	ChromeOptions options = new ChromeOptions();
	options.addArguments("start-maximized");
	
	Use a Chrome executable in a non-standard location
	ChromeOptions options = new ChromeOptions();
	options.setBinary("/path/to/other/chrome/binary");
	
	//Block dialog windows
	ChromeOptions options = new ChromeOptions();options.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popupblocking"));
	
	//Set download directory
	ChromeOptions options = new ChromeOptions();
	Map<String, Object> prefs = new HashMap<String, Object>();
	prefs.put("download.default_directory", "/directory/path");
	options.setExperimentalOption("prefs", prefs);
	
## Cookie(s)
	Code: //Delete all cookies
		driver.manage().deleteAllCookies();
		//Delete a specific Cookie
		driver.manage().deleteCookieNamed("name of the cookie=>eg: sessionKey");
		
		Set<Cookie> cookies = driver.manage().getCookies();
	
	//Get all cookies
	for (Cookie ck : cookies) {
	    System.out.println("Cookie Name: " + ck.getName());
	    System.out.println("Cookie Value: " + ck.getValue());
	    System.out.println("Cookie Domain: " + ck.getDomain());
	    System.out.println("Cookie Expiry: " + ck.getExpiry());
	    System.out.println("----------------------");
	}
	
	//Get a specific cookie by name
	Cookie sessionCookie = driver.manage().getCookieNamed("JSESSIONID");
	System.out.println("Session Cookie Value: " + sessionCookie.getValue());
	
	//Add a new cookie
	Cookie newCookie = new Cookie("testCookie", "12345");
	driver.manage().addCookie(newCookie);
	System.out.println("New cookie added successfully!");
	
## Taking Screenshot
	Package: 	import java.io.File;
				import org.apache.commons.io.FileUtils;
				import org.openqa.selenium.OutputType;
				import org.openqa.selenium.TakesScreenshot;
	Code:		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/screenshots/screenshot.png"));
	
## Broken Links Identification and SoftAssertion
	Package:	import java.io.IOException;
				import java.net.HttpURLConnection;
				import java.net.URL;
				import org.testng.asserts.SoftAssert;
	Code:	SoftAssert softAssert = new SoftAssert();
		List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
			int responseCode = connection.getResponseCode();
			// System.out.println(responseCode);
			softAssert.assertTrue(responseCode < 400,
					"The Broken link is: " + link.getText() + " with the status Code: " + responseCode);
		}
		driver.quit();
		softAssert.assertAll();
		
## Streams Java
	Package: import java.util.stream.Stream;
	Code:	Stream.of("apple", "alpha", "banana", "gauva", "butter", "box", "papaya", "button", "best", "bata", "beta",
				"bad", "cucumber").filter(name -> name.endsWith("a")).map(String::toUpperCase)
				.forEach(System.out::println);
			//Another example
			List<String> list = Arrays.asList("banana", "butter", "box", "button", "best", "bata", "beta", "bad");
			System.out.println("**********Sort the list starts with 'b' to upper case and print them******************");
			list.stream().filter(name -> name.startsWith("b")).sorted().map(String::toUpperCase)
				.forEach(System.out::println);
				
## Selenium 4 - Relative Locators
	Package: import static org.openqa.selenium.support.locators.RelativeLocator.with;
	Code: 	1. Above:
				WebElement username = driver.findElement(By.cssSelector("input[name='name']"));
				String text = driver.findElement(with(By.tagName("label")).above(username)).getText();
				System.out.println(text);
			2. Below:
				driver.findElement(with(By.tagName("input")).below(By.xpath("//label[@for='dateofBirth']"))).click();
			3. toLeftOf:
				driver.findElement(with(By.tagName("input")).toLeftOf(By.xpath("//label[text()='Check me out if you Love 					IceCreams!']"))).click();
			4. toRightOf:
				String labelText = driver.findElement(with(By.tagName("label")).toRightOf(By.id("inlineRadio1"))).getText();
				System.out.println(labelText);
				
## Selenium 4 - Opening Multiple TABs/WINDOWs
	Package: import org.openqa.selenium.WindowType;
	Code: driver.get("https://www.google.com/");
		//driver.switchTo().newWindow(WindowType.TAB);
		driver.switchTo().newWindow(WindowType.WINDOW);
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> windows = windowHandles.iterator();
		String parentWindow = windows.next();
		String childWindow = windows.next();
		driver.switchTo().window(childWindow);
		driver.switchTo().window(parentWindow);
		
## Selenium 4 - Taking Element Screenshot
	Package:	import java.io.File;
				import java.io.IOException;
				import org.apache.commons.io.FileUtils;
				import org.openqa.selenium.OutputType;
	Code:	WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
			name.sendKeys(courseName);
			File file =name.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/screenshot/elementScreenshot.png"));
			
## Selenium 4 - Capturing Height and Width of an Element
	Code:	WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
			System.out.println(name.getRect().getDimension().getHeight());
			System.out.println(name.getRect().getDimension().getWidth());
			
## TestNG - testng.xml -> Class Level
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
	<suite name="Suite">
		<test name="Test">
			<classes>
				<class name="selenium.learning.java.testNG.Test1" />
				<class name="selenium.learning.java.testNG.Test2" />
			</classes>
		</test> <!-- Test -->
		<test name="webapp">
			<classes>
				<class name="selenium.learning.java.testNG.WebApp">
					<methods>
						<!-- <exclude name="webacess" /> --> <!-- webaccess method is not executed from 'WebApp' class -->
						<exclude name="web.*" /> <!-- all the methods whose name starts with 'web' is not executed from 'WebApp' 
							class -->
					</methods>
				</class>
				<class name="selenium.learning.java.testNG.MobileApp">
					<methods>
						<!-- only below methods gets executed from 'MobileApp' class -->
						<include name="mobilelogin" />
						<include name="mobiletest" />
						<include name="mobileacess" />
					</methods>
				</class>
			</classes>
		</test>
	</suite> <!-- Suite -->

## TestNG - testng.xml -> Package Level
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
	<suite name="PackageRunSuite">
		<test name="TestPackageRun">
			<packages>
				<package name="selenium.learning.java.testNG" />
			</packages>
		</test>
	</suite> <!-- Suite -->	
	
## TestNG - Annotations Hierarchy:
	@BeforeSuite -> @BeforeTest -> @BeforeClass -> @BeforeMethod -> @Test -> @AfterMethod -> @AfterClass -> @AfterTest -> @AfterSuite
	
## TestNG - Groups - Include
	testng.xml file:
		<?xml version="1.0" encoding="UTF-8"?>
		<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
		<suite name="Suite">
			<test name="Test">
				<groups>
					<run>
						<!-- <include name="smoke" /> -->
						<include name="regression" />
					</run>
				</groups>
				<classes>
					<class name="selenium.learning.java.testNG.Test1" />
					<class name="selenium.learning.java.testNG.Test2" />
					<class name="selenium.learning.java.testNG.WebApp" />
					<class name="selenium.learning.java.testNG.MobileApp" />
				</classes>
			</test> <!-- Test -->
		</suite> <!-- Suite -->
	###Test1.java class:
		package selenium.learning.java.testNG;
		import org.testng.annotations.AfterClass;
		import org.testng.annotations.AfterMethod;
		import org.testng.annotations.AfterSuite;
		import org.testng.annotations.AfterTest;
		import org.testng.annotations.BeforeClass;
		import org.testng.annotations.BeforeMethod;
		import org.testng.annotations.BeforeSuite;
		import org.testng.annotations.BeforeTest;
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
		
			@Test(groups = { "regression" })
			public void test2() {
				System.out.println("Test2");
			}
		
		}
## TestNG - Groups - Exclude
	testng.xml
		<?xml version="1.0" encoding="UTF-8"?>
		<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
		<suite name="Suite">
			<test name="Test">
				<groups>
					<run>
						<!-- <include name="smoke" /> -->
						<!-- <include name="regression" /> -->
						<exclude name="smoke" />
					</run>
				</groups>
				<classes>
					<class name="selenium.learning.java.testNG.Test1" />
					<class name="selenium.learning.java.testNG.Test2" />
					<class name="selenium.learning.java.testNG.WebApp" />
					<class name="selenium.learning.java.testNG.MobileApp" />
				</classes>
			</test> <!-- Test -->
		</suite> <!-- Suite -->
				
## TestNG - dependsOnMethods attribute: @Test(dependsOnMethods={"test5"})
	@Test(groups = { "regression" },dependsOnMethods={"test5"})
	public void test3() {
		System.out.println("test3");
	}
	@Test(timeOut=5000)
	public void test5() {
		System.out.println("test5");
	}
## TestNG - Skipping a test case: @Test(enabled=false)
	@Test(enabled=false)
	public void test6() {
		System.out.println("test6");
	}
## TestNG - timeOut: @Test(timeOut=5000)
	@Test(timeOut=5000)
	public void test5() {
		System.out.println("test5");
	}
	
## TestNG - @Parameters Annotation:
	testng.xml file:
			<?xml version="1.0" encoding="UTF-8"?>
			<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
			<suite name="Suite">
				<parameter name="url" value="https://www.google.com"></parameter>
				<test name="Test">
					<classes>
						<class name="selenium.learning.java.testNG.Test1" />
						<class name="selenium.learning.java.testNG.Test2" />
					</classes>
				</test> <!-- Test -->
				<test name="webapp">
				<parameter name="url" value="webapp.com/URL" />
					<classes>
						<class name="selenium.learning.java.testNG.WebApp">
							<methods>
								<!-- <exclude name="webacess" /> --> <!-- webaccess method is not executed from 'WebApp' class -->
								<exclude name="web.*" /> <!-- all the methods whose name starts with 'web' is not executed from 'WebApp' 
									class -->
							</methods>
						</class>
						<class name="selenium.learning.java.testNG.MobileApp">
							<methods>
								<!-- only below methods gets executed from 'MobileApp' class -->
								<include name="mobilelogin" />
								<include name="mobiletest" />
								<include name="mobileacess" />
							</methods>
						</class>
					</classes>
				</test>
			</suite> <!-- Suite -->
			
			#####Java Code:
					@Parameters({"url"})
					@Test
					public void test2(String urlName) {
						System.out.println("Test2");
						System.out.println("URL value /is: "+urlName); // o/p: https://www.google.com
					}
					@Parameters({"url"})
					@Test(groups = { "smoke", "regression" })
					public void demo(String url) {
						System.out.println("web demo");
						System.out.println("Web demo url: "+url); // o/p: webapp.com/URL
					}
					
## TestNG - DataProvider
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
	
## TestNG - Listeners
		package selenium.learning.java.testNG;
		
		import org.testng.ITestContext;
		import org.testng.ITestListener;
		import org.testng.ITestResult;
		
		public class Listeners implements ITestListener{
		
			@Override
			public void onTestStart(ITestResult result) {
				// TODO Auto-generated method stub
				ITestListener.super.onTestStart(result);
			}
		
			@Override
			public void onTestSuccess(ITestResult result) {
				// TODO Auto-generated method stub
				ITestListener.super.onTestSuccess(result);
				System.out.println("I have successfully used/executed ITestListeners");
			}
		
			@Override
			public void onTestFailure(ITestResult result) {
				// TODO Auto-generated method stub
				ITestListener.super.onTestFailure(result);
				System.out.println("the test method failed is: "+result.getName());
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
				// TODO Auto-generated method stub
				ITestListener.super.onFinish(context);
			}
			
		
		}
## TestNG - Running Parallel: <suite name="Suite" parallel="tests" thread-count="5">
		<?xml version="1.0" encoding="UTF-8"?>
		<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
		<suite name="Suite" parallel="tests" thread-count="5">
			<parameter name="url" value="https://www.google.com"></parameter>
			<listeners>
			<listener class-name="selenium.learning.java.testNG.Listeners" />
			</listeners>
			<test name="Test">
			<parameter name="name" value="Apple" />
				<classes>
					<class name="selenium.learning.java.testNG.Test1" />
					<class name="selenium.learning.java.testNG.Test2" />
				</classes>
			</test> <!-- Test -->
			<test name="webapp">
			<parameter name="url" value="webapp.com/URL" />
			<parameter name="name" value="loginWebApp" />
				<classes>
					<class name="selenium.learning.java.testNG.WebApp">
						<methods>
							<!-- <exclude name="webacess" /> --> <!-- webaccess method is not executed from 'WebApp' class -->
							<exclude name="web.*" /> <!-- all the methods whose name starts with 'web' is not executed from 'WebApp' 
								class -->
						</methods>
					</class>
					<class name="selenium.learning.java.testNG.MobileApp">
						<methods>
							<!-- only below methods gets executed from 'MobileApp' class -->
							<include name="mobilelogin" />
							<include name="mobiletest" />
							<include name="mobileacess" />
						</methods>
					</class>
				</classes>
			</test>
		</suite> <!-- Suite -->
			